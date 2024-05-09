import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import authApi from '~/apis/auth.apis';
import { toast } from 'react-toastify';
import path from '~/constants/path';
import '../styles.css';
import SpinText from '~/components/spinloading/spinText';

const Register = () => {
  const navigate = useNavigate();
  const [fullName, setFullName] = React.useState('');
  const [username, setUsername] = React.useState('');
  const [password, setPassword] = React.useState('');
  const [email, setEmail] = React.useState('');
  const [phone, setPhone] = React.useState('');
  const [loading, setLoading] = React.useState(false);
  const [showPass, setShowPass] = React.useState(false);
  const handleShowPass = () => {
    setShowPass(!showPass);
  };
  const handleRegister = async () => {
    try {
      const phoneNumberRegex = /^0[0-9]{9}$/;
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      const usernameRegex = /^[a-z0-9]{6,15}$/;
      const passwordRegex = /\s/;
      if (!username) {
        toast.error('Hãy nhập tên tài khoản', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (!password) {
        toast.error('Hãy nhập mật khẩu', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (!email) {
        toast.error('Hãy nhập email', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (!fullName) {
        toast.error('Hãy nhập họ và tên', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (!phone) {
        toast.error('Hãy nhập số điện thoại', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (!usernameRegex.test(username)) {
        toast.error('Tên tài khoản không được chứa ký tự đặc biệt', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (passwordRegex.test(password)) {
        toast.error('Mật khẩu không được chứa dấu cách', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        return;
      }
      if (isNaN(Number(phone)) || !phoneNumberRegex.test(phone)) {
        toast.error('Số điện thoại không hợp lệ', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        setPhone('');
        return;
      }
      if (!emailRegex.test(email)) {
        toast.error('Email không hợp lệ', {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
        setEmail('');
        return;
      }
      const data = {
        username,
        password,
        fullName,
        email,
        phone,
      };
      setLoading(true);
      const res = await authApi.register(data);
      if (res.data.status) {
        setLoading(false);
        navigate(path.login);
        toast.error(`Đăng kí thành công`, {
          pauseOnHover: false,
          theme: 'dark',
        });
      } else {
        setLoading(false);
        toast.error(`${res.data.data}`, {
          position: 'top-right',
          pauseOnHover: false,
          theme: 'dark',
        });
      }
    } catch (error) {
      setLoading(false);
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="layout-account">
      <div className="container">
        <div className="wrapbox-content-account">
          <div className="userbox customers_accountForm">
            <div className="tab-form-account d-flex align-items-center justify-content-center">
              <h4>
                <Link to={path.login}>Đăng nhập</Link>
              </h4>
              <h4 className="active">
                <Link to={path.register}>Đăng ký</Link>
              </h4>
            </div>
            <div>
              <input name="form_type" type="hidden" defaultValue="create_customer" />
              <input name="utf8" type="hidden" defaultValue="✓" />
              <div className="clearfix large_form">
                <label htmlFor="last_name" className="label icon-field">
                  <i className="icon-login icon-user " />
                </label>
                <input
                  type="text"
                  placeholder="Họ và tên"
                  id="last_name"
                  className="text"
                  size={30}
                  value={fullName}
                  onChange={(e) => setFullName(e.target.value)}
                />
              </div>
              {/* <div className="clearfix large_form">
                <label htmlFor="first_name" className="label icon-field">
                  <i className="icon-login icon-user " />
                </label>
                <input
                  type="text"
                  placeholder="Tên"
                  id="first_name"
                  className="text"
                  size={30}
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                />
              </div> */}
              <div className="clearfix large_form">
                <label htmlFor="birthday" className="label icon-field">
                  <i className="icon-login icon-envelope " />
                </label>
                <input
                  type="text"
                  className="text"
                  placeholder="Số điện thoại"
                  size={30}
                  value={phone}
                  onChange={(e) => setPhone(e.target.value)}
                />
              </div>
              <div className="clearfix large_form">
                <label htmlFor="email" className="label icon-field">
                  <i className="icon-login icon-envelope " />
                </label>
                <input
                  type="email"
                  placeholder="Email"
                  id="email"
                  className="text"
                  size={30}
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                />
              </div>
              <div className="clearfix large_form">
                <label htmlFor="username" className="label icon-field">
                  <i className="icon-login icon-envelope " />
                </label>
                <input
                  type="text"
                  placeholder="Tên tài khoản"
                  id="username"
                  className="text"
                  size={30}
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                />
              </div>
              <div className="clearfix large_form large_form-mrb position-relative">
                <label htmlFor="password" className="label icon-field">
                  <i className="icon-login icon-shield " />
                </label>
                <input
                  type={showPass ? 'text' : 'password'}
                  placeholder="Mật khẩu"
                  id="password"
                  className="password text"
                  size={30}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                />
                {!showPass ? (
                      <svg
                        onClick={handleShowPass}
                        className="position-absolute"
                        width={18}
                        height={18}
                        style={{ marginTop: '18', marginLeft: '-30', cursor: 'pointer' }}
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 640 512"
                      >
                        <path d="M38.8 5.1C28.4-3.1 13.3-1.2 5.1 9.2S-1.2 34.7 9.2 42.9l592 464c10.4 8.2 25.5 6.3 33.7-4.1s6.3-25.5-4.1-33.7L525.6 386.7c39.6-40.6 66.4-86.1 79.9-118.4c3.3-7.9 3.3-16.7 0-24.6c-14.9-35.7-46.2-87.7-93-131.1C465.5 68.8 400.8 32 320 32c-68.2 0-125 26.3-169.3 60.8L38.8 5.1zm151 118.3C226 97.7 269.5 80 320 80c65.2 0 118.8 29.6 159.9 67.7C518.4 183.5 545 226 558.6 256c-12.6 28-36.6 66.8-70.9 100.9l-53.8-42.2c9.1-17.6 14.2-37.5 14.2-58.7c0-70.7-57.3-128-128-128c-32.2 0-61.7 11.9-84.2 31.5l-46.1-36.1zM394.9 284.2l-81.5-63.9c4.2-8.5 6.6-18.2 6.6-28.3c0-5.5-.7-10.9-2-16c.7 0 1.3 0 2 0c44.2 0 80 35.8 80 80c0 9.9-1.8 19.4-5.1 28.2zm51.3 163.3l-41.9-33C378.8 425.4 350.7 432 320 432c-65.2 0-118.8-29.6-159.9-67.7C121.6 328.5 95 286 81.4 256c8.3-18.4 21.5-41.5 39.4-64.8L83.1 161.5C60.3 191.2 44 220.8 34.5 243.7c-3.3 7.9-3.3 16.7 0 24.6c14.9 35.7 46.2 87.7 93 131.1C174.5 443.2 239.2 480 320 480c47.8 0 89.9-12.9 126.2-32.5zm-88-69.3L302 334c-23.5-5.4-43.1-21.2-53.7-42.3l-56.1-44.2c-.2 2.8-.3 5.6-.3 8.5c0 70.7 57.3 128 128 128c13.3 0 26.1-2 38.2-5.8z" />
                      </svg>
                    ) : (
                      <svg
                        onClick={handleShowPass}
                        className="position-absolute"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 576 512"
                        width={18}
                        height={18}
                        style={{ marginTop: '18', marginLeft: '-30', cursor: 'pointer' }}
                      >
                        <path d="M288 80c-65.2 0-118.8 29.6-159.9 67.7C89.6 183.5 63 226 49.4 256c13.6 30 40.2 72.5 78.6 108.3C169.2 402.4 222.8 432 288 432s118.8-29.6 159.9-67.7C486.4 328.5 513 286 526.6 256c-13.6-30-40.2-72.5-78.6-108.3C406.8 109.6 353.2 80 288 80zM95.4 112.6C142.5 68.8 207.2 32 288 32s145.5 36.8 192.6 80.6c46.8 43.5 78.1 95.4 93 131.1c3.3 7.9 3.3 16.7 0 24.6c-14.9 35.7-46.2 87.7-93 131.1C433.5 443.2 368.8 480 288 480s-145.5-36.8-192.6-80.6C48.6 356 17.3 304 2.5 268.3c-3.3-7.9-3.3-16.7 0-24.6C17.3 208 48.6 156 95.4 112.6zM288 336c44.2 0 80-35.8 80-80s-35.8-80-80-80c-.7 0-1.3 0-2 0c1.3 5.1 2 10.5 2 16c0 35.3-28.7 64-64 64c-5.5 0-10.9-.7-16-2c0 .7 0 1.3 0 2c0 44.2 35.8 80 80 80zm0-208a128 128 0 1 1 0 256 128 128 0 1 1 0-256z" />
                      </svg>
                    )}
              </div>

              <div className="clearfix custommer_account_action">
                <div className="action_bottom button">
                  <div className="button btn-signin" onClick={handleRegister}>
                    {loading ? <SpinText /> : <span>Đăng ký</span>}
                  </div>
                </div>
                <div className="req_pass">
                  Bạn đã có tài khoản?{' '}
                  <Link style={{ fontSize: '16px' }} to={path.login}>
                    <strong>Đăng nhập ngay</strong>
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Register;
