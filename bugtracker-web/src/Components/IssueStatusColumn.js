import React from 'react';
import IssueCard from "./IssueCard";
import '../Styles/IssueStatusColumn.css';

const IssueStatusColumn = props => (

    <div className="card shadow h-100 py-2">
        <div className="card-body border-bottom-primary">
            <div className="row no-gutters align-items-center">
                <div className="h5 mb-0 font-weight-bold text-gray-800">
                    {props.issueStatus}
                </div>
            </div>
        </div>
        <div className="issue-status-column-header">
            {props.issues.map(issue => <IssueCard issue={issue}/>)}
        </div>
    </div>
);

export default IssueStatusColumn;