import React from 'react'

export default function CreatePost(props) {
  return (
    <div>
        <div className='d-flex justify-content-center mt-4'>
            <div className='w-50'> 
                <textarea placeholder="What's on your mind?" className='w-100 p-2 non-resizable'
                value={props.content} onChange={props.postContentChanged} />
            </div>
        </div>
        <div className='d-flex justify-content-center'>
            <div className='d-flex justify-content-end w-50'>
                <button type='button' className='btn btn-secondary mb-4' onClick={props.postButtonClicked} disabled={!props.content}>Post</button>              
            </div>
        </div>
    </div>
  )
}
