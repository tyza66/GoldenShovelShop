import { createHashRouter, Navigate } from 'react-router-dom'
import Home from '../pages/home'
import Login from '../pages/login'

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
        path: '*',
        element: <Navigate to="/" />,
    },
])
