import { Route, Routes } from "react-router-dom";
import HomePage from "./PageComponents/HomePage";
import Header from "./NavbarComponent/Header";
import Signin from "./UserComponent/Signin";
import SignUp from "./UserComponent/SignUp"
import ContactUs from "./PageComponents/ContactUs";
import AboutUS from "./PageComponents/AboutUS";
import ViewMyOrders from "./OrderComponent/ViewMyOrder";
import ViewCart from "./CartComponent/VIewCart";
import Food from "./FoodComponents/Food";
import AddCardDetail from "./OrderComponent/AddCardDetail";
import PasswordReset from "./PasswordReset/PasswordReset";
import ChangePassword from "./PasswordReset/ChangePassword";

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
           <Route path="/customer/order/payment" element={<AddCardDetail/>}/>
           <Route path="/menu" element={<Food/>}/>
           <Route path="/user/customer/forgotPassword" element={<PasswordReset/>}/>
           <Route path="/user/customer/changePassword/:email" element={<ChangePassword/>}/>
           {/* your resturant route */}
       </Routes> 
    </div> );
}

export default Launcher;