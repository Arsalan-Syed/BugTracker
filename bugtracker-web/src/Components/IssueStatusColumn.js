import React from 'react';
import IssueCard from "./IssueCard";

const IssueStatusColumn = props => (
    <div className="card shadow h-100 py-2">
        <div className="card-body border-bottom-primary">
            <div className="row no-gutters align-items-center">
                <div className="h5 mb-0 font-weight-bold text-gray-800">
                    {props.issueStatus}
                </div>
            </div>
        </div>
        <div>
            <IssueCard issueName={"Issue #1"}/>
            <IssueCard issueName={"Issue #2"}/>
            <IssueCard issueName={"Issue #3"}/>
        </div>
    </div>
);

export default IssueStatusColumn;