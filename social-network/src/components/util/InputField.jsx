import React from 'react'

export default function InputField(props) {
  return (
    <div className={`form-floating mt-${props.mt} mb-${props.mb} `}>
        <input id={props.id} type={props.type} className='form-control form-control-lg' placeholder={props.placeholder} 
          name={props.name} value={props.value} onChange={props.changeHandler} />
        <label htmlFor={props.id} className='form-label text-muted' style={{'color': '#C0C0C0'}}>{props.placeholder}</label>
    </div>
  )
}
