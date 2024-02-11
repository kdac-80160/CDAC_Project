import { Route, Routes } from "react-router-dom";
import HomePage from "./PageComponents/HomePage";
import Header from "./NavbarComponent/Header";
import Signin from "./UserComponent/Signin";
import SignUp from "./UserComponent/SignUp"
import ContactUs from "./PageComponents/ContactUs";
import AboutUS from "./PageComponents/AboutUS";
import ViewMyOrders from "./OrderComponent/ViewMyOrder";
import ViewCart from "./CartComponent/VIewCart";

function Launcher() {
    return ( <div>
        <Header/>
       <Routes>
           <Route path="/" element={<HomePage/>}/>
           <Route path="/home" element={<HomePage/>}/>
           <Route path="/user/login" element={<Signin/>}/>
           <Route path="/user/customer/register" element={<SignUp/>}/>
           <Route path="/contactus" element={<ContactUs/>}/>
           <Route path="/aboutus" element={<AboutUS/>}/>
           <Route path="/customer/order" element={<ViewMyOrders/>}/>
           <Route path="/customer/cart" element={<ViewCart />}/>
       </Routes> 
    </div> );
}

export default Launcher;