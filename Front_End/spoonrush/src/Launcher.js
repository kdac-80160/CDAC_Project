import { Route, Routes } from "react-router-dom";
import HomePage from "./PageComponents/HomePage";
import Header from "./NavbarComponent/Header";
import Signin from "./UserComponent/Signin";
import SignUp from "./UserComponent/SignUp"
import ContactUs from "./PageComponents/ContactUs";
import AboutUS from "./PageComponents/AboutUS";
import ViewMyOrders from "./OrderComponent/ViewMyOrder";
import ViewCart from "./CartComponent/ViewCart";
import Food from "./FoodComponents/Food";
import PasswordReset from "./PasswordReset/PasswordReset";
import ChangePassword from "./PasswordReset/ChangePassword";
import PendingOrders from "./RestaurantComponent/PendingOrders";
import OnGoingOrder from "./RestaurantComponent/OngoingOrders";
import CancelledOrders from './RestaurantComponent/CancelledOrders'
import DeliveredOrders from './RestaurantComponent/DeliveredOrders'
import AddAddress from "./AddressCart/AddAddress";
import AddCartDetail from "./OrderComponent/AddCartDetail";
import NewOrderPage from './DeliveryComponent/NewOrderPage';
import OngoingOrderPage from './DeliveryComponent/OngoingOrderPage';
import DeliveredOrderPage from './DeliveryComponent/DeliveredOrderPage'
import Profile from './DeliveryComponent/Profile';
import DeliveryHome from './DeliveryComponent/DeliveryHome';
import AddFoodItem from './RestaurantComponent/AddFoodItem'

function Launcher() {
    return ( <div>
        <Header/>
       <Routes>
           <Route path="/" element={<HomePage/>}/>
           <Route path="/home" element={<HomePage/>}>
                <Route path="" element={<PendingOrders/>}></Route>
                <Route path="pending-order" element={<PendingOrders/>}/>
                <Route path="ongoing-order" element={<OnGoingOrder/>}/>
                <Route path="cancelled-order" element={<CancelledOrders/>}/>
                <Route path="delivered-order" element={<DeliveredOrders/>}/>
                <Route path="add-item" element={<AddFoodItem/>}/>
                <Route path="delivery/new-order" element={<NewOrderPage/>}/>
                <Route path="delivery/on-going-order" element={<OngoingOrderPage/>}/>
                <Route path="delivery/delivered-order" element={<DeliveredOrderPage/>}/>
            </Route>
           <Route path="/user/login" element={<Signin/>}/>
           <Route path="/user/customer/register" element={<SignUp/>}/>
           <Route path="/contactus" element={<ContactUs/>}/>
           <Route path="/aboutus" element={<AboutUS/>}/>
           <Route path="/customer/order" element={<ViewMyOrders/>}/>
           <Route path="/customer/cart" element={<ViewCart />}/>
           <Route path="/customer/order/payment" element={<AddCartDetail/>}/>
           <Route path="/menu" element={<Food/>}/>
           <Route path="/user/customer/forgotPassword" element={<PasswordReset/>}/>
           <Route path="/user/customer/changePassword/:email" element={<ChangePassword/>}/>
           <Route path="/addaddress" element={<AddAddress/>}/>
           <Route path="/delivery-person/profile" element={<Profile />} />
           <Route path="/delivery-person/deliveryhome" element={<DeliveryHome />} />
           {/* <Route path="/orders/pending" element={}/> */}
           {/* your resturant route */}
       </Routes> 
    </div> );
}

export default Launcher;