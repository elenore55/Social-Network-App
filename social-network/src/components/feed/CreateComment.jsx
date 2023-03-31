import React from 'react'

export default function CreateComment(props) {
  return (
    <div>
        <div className='d-flex justify-content-center mt-1 px-2'>
            <div className='w-50'> 
                <textarea placeholder="Your comment..." className='w-100 p-2 non-resizable'
                value={props.content} onChange={props.commentContentChanged} />
            </div>
        </div>
        <div className='d-flex justify-content-center'>
            <div className='d-flex justify-content-end w-50'>
                <button type='button' className='btn btn-secondary mb-4 me-2' onClick={props.postButtonClicked} disabled={!props.content}>Post</button>
            </div>
        </div>
    </div>
  )
}
