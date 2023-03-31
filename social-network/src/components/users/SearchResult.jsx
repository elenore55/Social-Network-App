import React, {useState, useEffect} from 'react'
import { useParams } from 'react-router-dom'
import SearchResultService from '../../services/SearchResultService';
import Navbar from '../main/Navbar';
import ShortUserDisplay from './ShortUserDisplay';
import ReceivedRequestsService from '../../services/ReceivedRequestsService';
import OtherUsersProfileService from '../../services/OtherUsersProfileService';
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom';

export default function SearchResult() {
	const [users, setUsers] = useState([]);
	const {term} = useParams();
	const navigate = useNavigate();

	useEffect(() => {
		fetchResults();
		// eslint-disable-next-line
	}, [term]);

	const acceptRequest = (senderId) => {
		ReceivedRequestsService.acceptRequest(senderId)
		.then(_res => {
			setUsers(prev => prev.map(user => user.id === senderId ? {...user, friendshipStatus: 'Friends'} : user))
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
			setUsers(prev => prev.map(user => user.id === senderId ? {...user, friendshipStatus: 'Not friends'} : user))
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

	const sendRequest = (receiverId) => {
		OtherUsersProfileService.sendFriendReq(receiverId)
		.then(_res => {
			setUsers(prev => prev.map(user => user.id === receiverId ? {...user, friendshipStatus: 'Request sent'} : user))
		})
		.catch(error => {
			if (error.response.status === 400) 
                toast.error(error.response.data.message)
            else if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to send request');
		});
	}

	const fetchResults = () => {
		SearchResultService.fetchResults(term)
		.then(res => {
			setUsers(res['data'].map(x => ({...x['userData'], friendshipStatus: x['friendshipStatus']})));
		})
		.catch(error => {
			if (error.response.status === 401)
                navigate('/unauth');
            else
                toast.error('Something went wrong: unable to fetch requests');
		});
	}

  return (
    <div className='profile-bg'>
      <Navbar />
	  {users.map(x => <ShortUserDisplay key={x.id} user={x} requestAccepted={acceptRequest} requestRejected={rejectRequest} requestSent={sendRequest} />)}
    </div>
  )
}
