import React from 'react'

export default function ProfilePictureModal(props) {
  return (
    <div className="modal" tabIndex="-1" id={props.id}>
        <div className="modal-dialog">
            <div className="modal-content">
                <div className="modal-header">
                    <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div className="modal-body d-flex justify-content-center">
                    <img src={props.profilePicture} alt='Profile' style={{width: '400px', height: '400px'}} />
                </div>
            </div>
        </div>
    </div>
  )
}
