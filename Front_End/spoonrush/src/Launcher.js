import { Route, Routes } from "react-router-dom";
import HomePage from "./PageComponents/HomePage";
import Header from "./NavbarComponent/Header";
import Signin from "./UserComponent/Signin";
import SignUp from "./UserComponent/SignUp"

function Launcher() {
    return ( <div>
        <Header/>
       <Routes>
           <Route path="/" element={<HomePage/>}/>
           <Route path="/user/login" element={<Signin/>}/>
           <Route path="/user/register" element={<SignUp/>}/>
       </Routes> 
    </div> );
}

export default Launcher;