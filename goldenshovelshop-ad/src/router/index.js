import { createHashRouter, Navigate } from 'react-router-dom'
import Home from '../pages/home'

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
        path: '*',
        element: <Navigate to="/" />,
    },
])
