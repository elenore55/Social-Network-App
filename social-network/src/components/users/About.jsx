import React from 'react'
import { faCake, faEnvelope } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'

export default function About(props) {
  return (
    <div className='m-5 d-flex justify-content-center'>
      <div>
        <div className='m-2'>
          <label><FontAwesomeIcon icon={faCake} size='2x'/> &nbsp; {props.birthday} </label>
        </div>

        <div className='m-2'>
          <FontAwesomeIcon icon={faEnvelope} size='2x'/> &nbsp; <label> {props.email} </label>
        </div>
      </div>
    </div>
  )
}
