import React from 'react';
import ChartistGraph from 'react-chartist';
import Chartist from 'chartist';
import frequency from '../../images/frequency.png';
import recency from '../../images/recency.png';
import monetary from '../../images/monetary.png';
import rfm from '../../images/rfm_distribution.png';

let dataSales = {
  labels: ['9:00AM', '12:00AM', '3:00PM', '6:00PM', '9:00PM', '12:00PM', '3:00AM', '6:00AM'],
  series: [
    [287, 385, 490, 492, 554, 586, 698, 695, 752, 788, 846, 944],
    [67, 152, 143, 240, 287, 335, 435, 437, 539, 542, 544, 647],
    [23, 113, 67, 108, 190, 239, 307, 308, 439, 410, 410, 509]
  ]
};

let optionsSales = {
  lineSmooth: true,
  low: 0,
  high: 800,
  showArea: true,
  height: "245px",
  axisX: {
    showGrid: false,
  },
  lineSmooth: Chartist.Interpolation.simple({
    divisor: 5
  }),
  showLine: false,
  showPoint: false
};

let responsiveSales = [
  ['screen and (max-width: 640px)', {
    axisX: {
      labelInterpolationFnc: function (value) {
        return value[0];
      }
    }
  }]
];

const UserBehaviour = () => (
  <div className="card">
    <div className="header">
      <h4 className="title">User Behavior</h4>
      <p className="category"></p>
    </div>
    <div className="content">
      {/* <ChartistGraph data={dataSales} options={optionsSales} responsiveOptions={responsiveSales} type="Line" className="ct-chart" /> */}
      <img src={rfm} width="1200"></img>
      <p>This distribution graph explains patterns generated for
        <li>Recency (R): Who have purchased recently? Number of days since last purchase</li>
        <li>Frequency (F): Who has purchased frequently? The total number of purchases</li>
        <li>Monetary Value(M): Who has high purchase amount? The total money customer spent
        which are used to identify type of customers .
      <p> If the customer bought in recent past, he gets higher points. If he bought many times, he gets higher score. And if he spent a lot of money, he gets more points.</p></li>
      </p>
      <img src={recency}></img>
      <p>Recency distribution is right-skewed, showing that the majority of customers made a purchase in the recent past.</p> 
        <p>It has a long tail containing single customers who made their order long time ago.</p>
      <img src={monetary}></img>
      <p>When it comes to monetary, it follows similar pattern like frequency. Part of customers have only spent 
      little amount of money but there is another (significant in size)</p><p>group that have spent much more.</p>
      <img src={frequency}></img>
      <p>The chart reveals that one group of customers has low frequency (small number of orders, around 10 per customer) 
        and another group is much more "loyal"</p><p>(with around 50 purchases per customer).</p>
    </div>
    {/* <div className="footer">
      <div className="legend">
        <div className="item">
          <i className="fa fa-circle text-info"></i> Open
        </div>
        <div className="item">
          <i className="fa fa-circle text-danger"></i> Click
        </div>
        <div className="item">
          <i className="fa fa-circle text-warning"></i> Click Second Time
        </div>
      </div>
      <hr />
      <div className="stats">
        <i className="fa fa-history"></i> Updated 3 minutes ago
          </div>
    </div> */}
  </div>
);

export default UserBehaviour;