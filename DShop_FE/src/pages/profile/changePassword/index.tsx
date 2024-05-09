import React, { useState } from 'react';
import LeftPage from '../component/leftPage';
import { toast } from 'react-toastify';
import SpinLoading from '~/components/spinloading';
import { User } from '~/types/user.type';
import { RootState } from '~/redux/reducers';
import { useSelector } from 'react-redux';
import '../styles.css';
import { useNavigate } from 'react-router-dom';
import path from '~/constants/path';
import userApi from '~/apis/user.apis';

const ChangePassWord = () => {
  const [showPass, setShowPass] = useState(false);
  const [oldPassword, setOldPassword] = React.useState('');
  const [newPassword, setNewPassword] = React.useState('');
  const [reNewpassword, setReNewPassword] = React.useState('');
  const [loading, setLoading] = React.useState(false);
  const user1: User = useSelector((state: RootState) => state.AuthReducer.user);
  const navigate = useNavigate();

  const handleShowPass = () => {
    setShowPass(!showPass);
  };

  const handleChangePass = async () => {
    try {
      const passwordRegex = /\s/;
      setLoading(true);
      if (!oldPassword) {
        toast.error(`Vui lòng nhập mật khẩu cũ`, {
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (!newPassword) {
        toast.error(`Vui lòng nhập mật khẩu mới`, {
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (!reNewpassword) {
        toast.error(`Vui lòng nhập lại mật khẩu mới`, {
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (passwordRegex.test(oldPassword) || passwordRegex.test(newPassword) || passwordRegex.test(reNewpassword)) {
        toast.error('Mật khẩu không được chứa dấu cách', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      const data = {
        oldPassword: oldPassword,
        newPassword: newPassword,
        cfNewPassword: reNewpassword,
      };
      const res = await userApi.changePassword(user1.id, data);
      if (res.data.status) {
        setLoading(false);
        toast.success(
          <div>
            {res.data.data}. <br />
            Vui lòng đăng nhập lại!
          </div>,
          {
            pauseOnHover: false,
            theme: 'dark',
          },
        );
        navigate(path.login);
      } else {
        setLoading(false);
        toast.error(res.data.data, {
          pauseOnHover: false,
          theme: 'dark',
        });
      }
    } catch (error) {
      setLoading(true);
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="layout-account change_password">
      <div className="container">
        <div className="wrapbox-content-account">
          <div className="header-page clearfix">
            <h1>Đổi mật khẩu </h1>
          </div>
          <div className="row">
            <LeftPage />
            <div className="col-lg-9 col-md-12 col-12">
              <div className="clearfix large_form">
                <input
                  type={showPass ? 'text' : 'password'}
                  placeholder="Nhập mật khẩu cũ"
                  className="text col"
                  value={oldPassword}
                  onChange={(e) => setOldPassword(e.target.value)}
                />
              </div>
              <div className="clearfix large_form mt-2">
                <input
                  type={showPass ? 'text' : 'password'}
                  placeholder="Nhập mật khẩu mới"
                  className="text col"
                  value={newPassword}
                  onChange={(e) => setNewPassword(e.target.value)}
                />
              </div>
              <div className="clearfix large_form mt-2">
                <input
                  type={showPass ? 'text' : 'password'}
                  placeholder="Nhập lại mật khẩu mới"
                  className="text col"
                  value={reNewpassword}
                  onChange={(e) => setReNewPassword(e.target.value)}
                />
              </div>
              <div className="clearfi mt-3">
                <div className="show-password d-flex align-items-center">
                  <svg
                    onClick={handleShowPass}
                    style={{ width: '18px', height: '18px', cursor: 'pointer', fill: showPass ? '#044581' : '#363535' }}
                    xmlns="http://www.w3.org/2000/svg"
                    viewBox="0 0 448 512"
                  >
                    <path d="M64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64zM337 209L209 337c-9.4 9.4-24.6 9.4-33.9 0l-64-64c-9.4-9.4-9.4-24.6 0-33.9s24.6-9.4 33.9 0l47 47L303 175c9.4-9.4 24.6-9.4 33.9 0s9.4 24.6 0 33.9z" />
                  </svg>
                  <span style={{ marginLeft: '5px' }}>Hiện mật khẩu</span>
                </div>
              </div>
              <div style={{ width: '20%' }} className="clearfi mt-3">
                {loading ? (
                  <div className="spinload">
                    <SpinLoading />
                  </div>
                ) : (
                  <div className="button btn-signin" onClick={handleChangePass}>
                    <span>Đổi mật khẩu</span>
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default ChangePassWord;
