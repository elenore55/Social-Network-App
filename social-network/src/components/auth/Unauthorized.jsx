import React from 'react'
import { Link } from 'react-router-dom'

export default function Unauthorized() {
  return (
    <div className='mt-3'>
      <h1 className='d-flex justify-content-center'>Unauthorized</h1>
      <h2 className='d-flex justify-content-center'>Access denied</h2>
      <Link className='d-flex justify-content-center' to='/'><h4>Log in</h4></Link>
    </div>
  )
}
