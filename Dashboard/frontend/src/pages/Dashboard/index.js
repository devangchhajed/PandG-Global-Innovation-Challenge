import React from 'react';
import Density from './Density';
import UserBehaviour from './UserBehaviour';
import PieChart from './PieChart';
import CategoryScores from './CategoryScores';

const Dashboard = () => (
  <div className="content">
    <div className="container-fluid">
      <div className="row">
        {/* <div className="col-md-4">
          <EmailChart />
        </div> */}
        <div className="col-md-12">
          <UserBehaviour />
        </div>
      </div>
      <div className="row">
        <div className="col-md-12">
          <PieChart />
        </div>
        </div>
        <div className="row">
        <div className="col-md-12">
          <Density />
        </div>
      </div>
      <div className="row">
        <div className="col-md-12">
          <CategoryScores />
        </div>
      </div>
    </div>
  </div>
);

export default Dashboard;