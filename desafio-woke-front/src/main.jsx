import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './App.css'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'

import routes from '~react-pages'
import { ToastContainer } from 'react-toastify'

console.log(routes)

const router = createBrowserRouter(routes)

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <div className="App">
      <ToastContainer />
      <RouterProvider router={router} />
    </div>
    
  </React.StrictMode>,
)
