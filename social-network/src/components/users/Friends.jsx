import React, {useState, useEffect} from 'react'
import ShortUserDisplay from './ShortUserDisplay'
import FriendsService from '../../services/FriendsService';
import { toast } from 'react-toastify'
import { useNavigate } from 'react-router-dom';

export default function Friends() {
    const [users, setUsers] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetchUsers();
    });

    const fetchUsers = () => {
      FriendsService.fetchFriends()
        .then(res => {
            setUsers(res.data.map(x => ({...x.userData, friendshipStatus: x.friendshipStatus})))
        })
        .catch(error => {
        	if (error.response.status === 401)
        		navigate('/unauth');
          	else
            	toast.error('Something went wrong: unable to fetch users');
        })
    }

  return (
    <div>
      {users.map(x => <ShortUserDisplay key={x.id} user={x} />)}
    </div>
  )
}
