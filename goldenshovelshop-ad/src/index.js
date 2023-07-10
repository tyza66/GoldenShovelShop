import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

import { RouterProvider } from 'react-router-dom'
import { globalRouters } from './router/index'
import { ConfigProvider } from 'antd'
import zhCN from 'antd/locale/zh_CN'


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <ConfigProvider locale={zhCN}>
    <App />
    <RouterProvider router={globalRouters} />
  </ConfigProvider>
);

reportWebVitals();
