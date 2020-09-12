import React from 'react';

const IssueCard = props => (
    <div class="card shadow mb-4">
        <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
            <h6 class="m-0 font-weight-bold text-primary">{props.issueName}</h6>
            <div class="dropdown no-arrow">
                <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                </a>
                <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in" aria-labelledby="dropdownMenuLink">
                    <div class="dropdown-header">Options</div>
                    <a class="dropdown-item" href="#">Move to <b>In Progress</b></a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" href="#">Delete issue</a>
                </div>
            </div>
        </div>
        <div class="card-body">
            Finish up this website
        </div>
    </div>
);

export default IssueCard;