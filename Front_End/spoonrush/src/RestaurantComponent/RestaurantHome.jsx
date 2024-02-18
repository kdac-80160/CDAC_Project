
import { NavLink, Outlet } from "react-router-dom";

import "../../node_modules/bootstrap/dist/css/bootstrap.min.css";

function RestaurantHome() {
  
  return (
    <div className="row">
      <div className="col-2">
        <NavLink
          className={"link-offset-2 link-underline link-underline-opacity-0 btn btn-primary m-2"} style={{width:200}} to={"/home/pending-order"}
        >
          Pending Orders
        </NavLink>
        <br/>
        <NavLink
          className={"link-offset-2 link-underline link-underline-opacity-0 btn btn-primary m-2"} style={{width:200}} to={'/home/ongoing-order'}
        >
          Ongoing Orders
        </NavLink>
        <br />
        <NavLink
          className={"link-offset-2 link-underline link-underline-opacity-0 btn btn-primary m-2"} style={{width:200}} to={'/home/cancelled-order'}
        >
          Cancelled Orders
        </NavLink>
        <br />
        <NavLink
          className={"link-offset-2 link-underline link-underline-opacity-0 btn btn-primary m-2"} style={{width:200}} to={'/home/delivered-order'}
        >
          Delivered Orders
        </NavLink>
      </div>
      <Outlet/>
      
    </div>
  );
}

export default RestaurantHome;
