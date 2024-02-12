import React from 'react';
import Footer from '../NavbarComponent/Footer';

function ContactUs() {
  return (
    <div className='d-flex flex-column' style={{ minHeight: '100vh' }}>
      <div className='container flex-grow-1'>
        <div className="row justify-content-center mt-5">
          <div className="col-md-8">
            <div className="text-color">
              <h4 className="mb-4">Contact Us</h4>
              <p>
                We value your feedback, questions, and inquiries. Whether you need assistance with an order, have a suggestion, or simply want to connect, our team is here to help. Feel free to reach out through the provided contact form, email, or phone number, and we'll respond promptly to ensure your experience with us is nothing short of exceptional. Your satisfaction is our priority, and we look forward to hearing from you.
              </p>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ContactUs;
