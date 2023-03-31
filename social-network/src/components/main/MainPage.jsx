import React from 'react'
import Navbar from './Navbar'
import PostList from '../feed/PostList'

export default function MainPage() {

  return (
    <div className='profile-bg'>
      <Navbar />
      <PostList path='posts' />
    </div>
  )
}
