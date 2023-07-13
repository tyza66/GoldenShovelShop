import { createHashRouter, Navigate } from 'react-router-dom'
import Home from '../pages/home'
import Login from '../pages/login'
import Register from '../pages/register'
import Recharge from '../pages/recharge'
import Rechargeok from '../pages/rechargeOK'

export const globalRouters = createHashRouter([
    {
        path: '/',
        element: <Home />,
    },
    {
        path: '/home',
        element: <Home />,
    },
    {
        path: '/login',
        element: <Login />,
    },
    {
        path: '/register',
        element: <Register />,
    },
    {
        path: '/recharge',
        element: <Recharge />,
    },
    {
        path: '/rechargeok',
        element: <Rechargeok />,
    },
    {
        path: '*',
        element: <Navigate to="/" />,
    },
])
