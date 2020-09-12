import React from 'react';
import Modal from "react-bootstrap/Modal";
import '../Styles/IssueCard.css'

const IssueCard = props => {

    const [isOpen, setIsOpen] = React.useState(false);


    const showModal = () => {
        setIsOpen(true);
    }

    const hideModal = () => {
        setIsOpen(false);
    }

    return(
        <div>
            <Modal show={isOpen}>
                <Modal.Header>
                    <Modal.Title>
                        {props.issueName}
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>Body</Modal.Body>
                <Modal.Footer>
                    <button className="btn btn-danger" onClick={hideModal}>Cancel</button>
                    <button className="btn btn-primary" onClick={hideModal}>Save</button>
                </Modal.Footer>
            </Modal>

            <div className="card shadow mb-4">
                <div className="card-header py-3 d-flex flex-row align-items-center justify-content-between" onClick={showModal} >
                    <h6 className="m-0 font-weight-bold text-primary">{props.issueName}</h6>
                    <div className="dropdown no-arrow">
                        <a className="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <i className="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                        </a>
                        <div className="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                             aria-labelledby="dropdownMenuLink">
                            <div className="dropdown-header">Options</div>
                            <a className="dropdown-item" href="#">Move to <b>In Progress</b></a>
                            <div className="dropdown-divider"></div>
                            <a className="dropdown-item text-danger" href="#">Delete issue</a>
                        </div>
                    </div>
                </div>
                <div className="card-body">
                    Finish up this website
                </div>
            </div>
        </div>


    );
};

export default IssueCard;