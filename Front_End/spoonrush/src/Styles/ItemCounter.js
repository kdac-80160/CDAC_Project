import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';




function ItemCounter(food) {
    
    const [count, setCount] = useState(0);
    const [showIncrementDecrement, setShowIncrementDecrement] = useState(false);
    const navigate = useNavigate();

    const customer_jwtToken = sessionStorage.getItem("jwtToken");
   
  
    const itemCounterClickHandler = () => {
      
        if(customer_jwtToken===null){
            console.log(customer_jwtToken);
            navigate('/user/login');
        }
        else
         if(showIncrementDecrement===false)
        {
           setShowIncrementDecrement(true);
           incrementCount();

        }
        console.log(food);
        
    };
    const incrementCount = () => {
      const e=food.item.id;
      setCount(count + 1);
      fetch(`https://localhost:8443/cart/add/${e}`, {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + customer_jwtToken,
        },
       
      })
        .then((result) => {
          result.json().then((res) => {
            if (res.success) {
              toast.success(res.responseMessage, {
                position: "top-center",
                autoClose: 1000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
              });
  
            } else if (!res.success) {
              toast.error(res.responseMessage, {
                position: "top-center",
                autoClose: 1000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
              });
               // Redirect after 3 seconds
            }
          });
        })
        .catch((error) => {
          console.error(error);
          toast.error("It seems server is down", {
            position: "top-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
          });
        });
    };
  
    const decrementCount = ( ) => {
      const e=food.item.id;
      if (count <= 1) {
        setShowIncrementDecrement(false);   
    }
     setCount(count - 1);
        console.log(e);
  
      fetch(`https://localhost:8443/cart/remove/${e}`, {
        method: "GET",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
          Authorization: "Bearer " + customer_jwtToken,
        },
        
      })
        .then((result) => {
          result.json().then((res) => {
            if (res.success) {
              toast.success(res.responseMessage, {
                position: "top-center",
                autoClose: 1000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
              });
  
              setTimeout(() => {
                window.location.reload(true);
              }, 1000); // Redirect after 3 seconds
            } else if (!res.success) {
              toast.error(res.responseMessage, {
                position: "top-center",
                autoClose: 1000,
                hideProgressBar: false,
                closeOnClick: true,
                pauseOnHover: true,
                draggable: true,
                progress: undefined,
              });
              setTimeout(() => {
                window.location.reload(true);
              }, 1000); // Redirect after 3 seconds
            }
          });
        })
        .catch((error) => {
          console.error(error);
          toast.error("It seems server is down", {
            position: "top-center",
            autoClose: 1000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
          });
           setTimeout(() => {
             window.location.reload(true);
         }, 1000); // Redirect after 3 seconds
        });
     };

    return (
        
            <div>
                <button onClick={itemCounterClickHandler} className="btn bg-color custom-bg-text">
                    {showIncrementDecrement ? (
                        <>
                            <span className='btn bg-color custom-bg-text' onClick={incrementCount}>+</span>
                            <span>{count}</span>
                            <span className='btn bg-color custom-bg-text' onClick={decrementCount}>-</span>
                        </>
                    ) : (
                        'Add To Cart'
                    )}
                   
                </button>
            </div>
        
    );
}

export default ItemCounter;