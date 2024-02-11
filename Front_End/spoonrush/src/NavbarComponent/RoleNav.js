import React from 'react'
import NormalHeader from './NormalHeader';
import DeliveryHeader from './DeliveryHeader';
import AdminHeader from './AdminHeader';
import UserHeader from './UserHeader';

const RoleNav = () => {
    const user = JSON.parse(sessionStorage.getItem("active-customer"));
  const admin = JSON.parse(sessionStorage.getItem("active-admin"));
  const deliveryPerson = JSON.parse(sessionStorage.getItem("active-delivery"));
  if (user != null) {
    return <UserHeader />;
  } else if (admin != null) {
    return <AdminHeader />;
  } else if (deliveryPerson != null) {
    return <DeliveryHeader/>;
  } else {
    return <NormalHeader/>;
  }
  
};

export default RoleNav
