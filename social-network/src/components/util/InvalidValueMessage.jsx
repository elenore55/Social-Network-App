import React from 'react'

export default function InvalidValueMessage(props) {
  return (
    <p style={{color: 'red'}}>{ props.message }</p>
  )
}
