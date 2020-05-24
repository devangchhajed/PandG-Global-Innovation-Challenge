import React from 'react';
import Chart from 'react-chartist';
import piechart from '../../images/piechart.png';

let data = {
  labels: ['Jan', 'Feb', 'Mar', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
  series: [
    [542, 443, 320, 780, 553, 453, 326, 434, 568, 610, 756, 895],
    [412, 243, 280, 580, 453, 353, 300, 364, 368, 410, 636, 695]
  ]
};

let options = {
  seriesBarDistance: 10,
  axisX: {
    showGrid: false
  },
  height: "245px"
};

let responsiveOptions = [
  ['screen and (max-width: 640px)', {
    seriesBarDistance: 5,
    axisX: {
      labelInterpolationFnc: function (value) {
        return value[0];
      }
    }
  }]
];

const PieChart = () => (
  <div className="card ">
    <div className="header">
      <h4 className="title">Distribution Of Categories Among Customers</h4>
      <p className="category">PieChart</p>
    </div>
    <div className="content">
      {/* <Chart data={data} options={options} responsiveOptions={responsiveOptions} type="Bar" className="ct-chart" /> */}
      <img src={piechart} width="1200" className="center"></img>
      <p>The pie chart explains various customer segments created obtained as following:
        <ul>
        <li>Champions - bought recently, buy often and spend the most</li>
        <li>Loyal Customers - spend good money and often, responsive to promotions</li>
        <li>Potential Loyalist - recent customers, but spent a good amount and bought more than once</li>
        <li>New Customers - bought most recently, but not often</li>
        <li>Promising - recent shoppers, but haven’t spent much</li>
        <li>Needing Attention - above average recency, frequency and monetary values; may not have bought very recently though</li>
        <li>About To Sleep - below average recency, frequency and monetary values; will lose them if not reactivated</li>
        <li>At Risk - spent big money and purchased often but long time ago; need to bring them back</li>
        <li>Can't Loose Them - made biggest purchases, and often but haven’t returned for a long time</li>
        <li>Hibernating - last purchase was long back, low spenders and low number of orders</li>
      </ul>
</p>
    </div>
    {/* <div className="footer">
      <div className="legend">
        <div className="item">
          <i className="fa fa-circle text-info"></i> Tesla Model S
        </div>
        <div className="item">
          <i className="fa fa-circle text-danger"></i> BMW 5 Series
        </div>
      </div>
      <hr />
       <div className="stats">
        <i className="fa fa-check"></i> Data information certified
          </div> 
    </div> */}
  </div>
);

export default PieChart;