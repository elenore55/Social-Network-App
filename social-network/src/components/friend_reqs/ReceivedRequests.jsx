import React, {useState, useEffect} from 'react'
import Navbar from '../main/Navbar'
import ReceivedRequestsService from '../../services/ReceivedRequestsService'
import FriendRequest from './FriendRequest';
import { toast } from 'react-toastify';
import { useNavigate } from 'react-router-dom';

export default function ReceivedRequests() {
    const [requests, setRequests] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchRequests();
    }, []);

    const acceptRequest = (senderId) => {
        ReceivedRequestsService.acceptRequest(senderId)
        .then(_res => {
            toast.success('Request accepted');
            setRequests(prev => prev.filter(x => x.otherUser.id !== senderId));
        })
        .catch(error => {
            if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to accept request');
        });
    }

    const rejectRequest = (senderId) => {
        ReceivedRequestsService.rejectRequest(senderId)
        .then(_res => {
            toast.success('Request rejected');
            setRequests(prev => prev.filter(x => x.otherUser.id !== senderId));
        })
        .catch(error => {
            if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to reject request');
        });
    }

    const fetchRequests = () => {
        ReceivedRequestsService.fetchRequests()
        .then(res => {
            setRequests(res.data);
        })
        .catch(error => {
            if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to fetch requests');
        })
    }

  return (
    <div className='profile-bg'>
        <Navbar selectedNavLink='friendReqs' />
        <h2 className='d-flex justify-content-center my-4 pt-4'>Friendship requests</h2>
        <div className='profile-bg'>
            {requests.map(x => <FriendRequest key={x.id} req={x} requestAccepted={acceptRequest} requestRejected={rejectRequest} />)}
        </div>
    </div>
  )
}
