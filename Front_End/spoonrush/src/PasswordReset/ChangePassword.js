import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ChangePassword = () => {
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const handleInputChange = (e, setter) => {
        setter(e.target.value);
    };

    const handleChangePassword = () => {
        if (newPassword !== confirmPassword) {
            toast.error("New password and confirm password do not match.");
            return;
        }

        // Logic to change password
        // Example: You can make a fetch call to your backend API to change the password
        // Example: fetch('/api/change-password', { method: 'POST', body: { oldPassword, newPassword } })

        // For demonstration, let's assume the password change is successful
        toast.success("Password changed successfully.");

        setOldPassword("");
        setNewPassword("");
        setConfirmPassword("");
    };

    return (
        <div>
            <div className="mt-2 d-flex aligns-items-center justify-content-center">
                <div className="form-card border-color" style={{ width: "37rem" }}>
                    <div className="card-header bg-color custom-bg-text mt-2 d-flex justify-content-center align-items-center" style={{ borderRadius: "0em", height: "38px" }}>
                        <h4 className="card-title">Change Password</h4>
                    </div>
                    <div className="card-body mt-3">
                        <form>
                            <div className="mb-3 text-color">
                                <label htmlFor="oldPassword" className="form-label">
                                    <b>Old Password</b>
                                </label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="oldPassword"
                                    name="oldPassword"
                                    value={oldPassword}
                                    onChange={(e) => handleInputChange(e, setOldPassword)}
                                />
                            </div>
                            <div className="mb-3 text-color">
                                <label htmlFor="newPassword" className="form-label">
                                    <b>New Password</b>
                                </label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="newPassword"
                                    name="newPassword"
                                    value={newPassword}
                                    onChange={(e) => handleInputChange(e, setNewPassword)}
                                />
                            </div>
                            <div className="mb-3 text-color">
                                <label htmlFor="confirmPassword" className="form-label">
                                    <b>Confirm Password</b>
                                </label>
                                <input
                                    type="password"
                                    className="form-control"
                                    id="confirmPassword"
                                    name="confirmPassword"
                                    value={confirmPassword}
                                    onChange={(e) => handleInputChange(e, setConfirmPassword)}
                                />
                            </div>
                            <div className="d-flex aligns-items-center justify-content-center mb-2">
                                <button
                                    type="button"
                                    className="btn bg-color custom-bg-text"
                                    onClick={handleChangePassword}
                                >
                                    Change Password
                                </button>
                            </div>
                            <ToastContainer />
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default ChangePassword;
