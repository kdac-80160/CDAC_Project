import React from "react";
import NormalHeader from "./NormalHeader";
import DeliveryHeader from "./DeliveryHeader";
import AdminHeader from "./AdminHeader";
import UserHeader from "./UserHeader";

const RoleNav = () => {
  const role = sessionStorage.getItem("role");
  console.log(role);
  if (role === "ROLE_CUSTOMER") {
    return <UserHeader />;
  } else if (role === "ROLE_MANAGER") {
    return <AdminHeader />;
  } else if (role === "ROLE_DELIVERY") {
    return <DeliveryHeader />;
  } else {
    return <NormalHeader />;
  }
};

export default RoleNav;
