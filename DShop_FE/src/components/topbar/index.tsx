import React from 'react';
import { Link } from 'react-router-dom';
import Images from '~/static';
import { useNavigate } from 'react-router-dom';

const Topbar = () => {
  const navigate = useNavigate();
  return (
    <div className="topbar bg-white">
      <div className="topbar-bottom">
        <div className="container">
            <div className="row ">
            <div className='location col-4 d-flex align-items-center' style={{color: 'white'}}>
              <svg fill='white' width="30" height="30" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 384 512"><path d="M215.7 499.2C267 435 384 279.4 384 192C384 86 298 0 192 0S0 86 0 192c0 87.4 117 243 168.3 307.2c12.3 15.3 35.1 15.3 47.4 0zM192 128a64 64 0 1 1 0 128 64 64 0 1 1 0-128z"/></svg>
              <div className="location-name d-flex flex-column ms-3 " style={{fontSize: '16px'}}>
                <span>HN:</span>
                <span>HCM:</span>
              </div>
            </div>
            <div className="wrap-logo col-4 text-center" onClick={() => navigate('/')}>
                    <a href="#">
                      <img
                        src={Images.logo}
                        alt="DShop"
                        className="img-responsive logoimg ls-is-cached lazyloaded"
                        style={{width: '170px', objectFit: 'cover', objectPosition: 'center' }}
                      />
                    </a>
                  </div>
              <div className="hotline d-flex align-items-center justify-content-end col-4 ">
                <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" viewBox="0 0 32 32" fill="white"><g clip-path="url(#clip0_10122_52416)"><path d="M4.81815 18.5599C5.37836 18.5599 5.8325 18.1058 5.8325 17.5456C5.8325 16.9854 5.37836 16.5313 4.81815 16.5313C4.25794 16.5313 3.8038 16.9854 3.8038 17.5456C3.8038 18.1058 4.25794 18.5599 4.81815 18.5599Z" fill="#333333"></path><path fill-rule="evenodd" clip-rule="evenodd" d="M11.7132 0.562359C12.463 -0.187453 13.6787 -0.187453 14.4285 0.562359L17.5537 3.68753C18.643 4.77688 18.643 6.54306 17.5537 7.63241L16.6571 8.52898L23.471 15.3429L24.3676 14.4463C25.4569 13.357 27.2231 13.357 28.3125 14.4463L31.4376 17.5715C32.1875 18.3213 32.1875 19.537 31.4376 20.2868C28.6711 23.0534 24.3264 23.28 21.3013 20.9666V25.1532C21.3013 26.6938 20.0524 27.9427 18.5118 27.9427H17.6006L17.1862 29.8074C16.8604 31.2736 14.9743 31.6977 14.0522 30.5121L12.0537 27.9427H2.78946C1.24888 27.9427 0 26.6938 0 25.1532V15.5169C0 13.9763 1.24888 12.7275 2.78946 12.7275H12.9658L11.7132 11.4748C8.69982 8.46144 8.69981 3.57576 11.7132 0.562359ZM14.4873 14.249L19.7798 19.5414V25.1532C19.7798 25.8535 19.2121 26.4211 18.5118 26.4211H16.9903C16.6338 26.4211 16.325 26.6688 16.2477 27.0169L15.7009 29.4773C15.6544 29.6868 15.3849 29.7474 15.2532 29.578L13.0263 26.7148C12.8821 26.5295 12.6605 26.4211 12.4258 26.4211H2.78946C2.08919 26.4211 1.52152 25.8535 1.52152 25.1532V15.5169C1.52152 14.8166 2.08919 14.249 2.78946 14.249H14.4873ZM13.3526 1.63824C13.197 1.48262 12.9447 1.48262 12.7891 1.63824C10.3699 4.05744 10.3699 7.97975 12.7891 10.399L21.601 19.2109C24.0202 21.6301 27.9426 21.6301 30.3618 19.2109C30.5174 19.0553 30.5174 18.803 30.3618 18.6474L27.2366 15.5222C26.7414 15.027 25.9386 15.027 25.4435 15.5222L24.009 16.9567C23.7119 17.2538 23.2302 17.2538 22.9331 16.9567L15.0433 9.06692C14.7462 8.76982 14.7462 8.28814 15.0433 7.99104L16.4778 6.55654C16.973 6.06138 16.973 5.25857 16.4778 4.76341L13.3526 1.63824Z" fill="white"></path></g><defs><clipPath id="svg-2579"><rect width="32" height="32" fill="white"></rect></clipPath></defs></svg>
                <span className='ms-3'>
                  0934.989.999
                  <br />
                  0357.624.999
                </span>
              </div>
            </div>
          
        </div>
      </div>
    </div>
  );
};

export default Topbar;
