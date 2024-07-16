import {FaLock, FaPhone, FaEnvelope, FaBirthdayCake, FaUser} from 'react-icons/fa';
import{ useState } from "react";
import './styles.css'
import { useNavigate } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';

const SignUp = () => {

    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [telephone, setTelephone] = useState("");
    const [birthDate, setBirthDate] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault()
        await fetch('http://localhost:8080/v1/api/auth/register', {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({
                name,
                telephone,
                email,
                password,
                birthDate,
            })
        })
        
        navigate('/login')
    }

  return (
    <>
    <ToastContainer />
    <div className="container">
          <form onSubmit={handleSubmit}>
              <h1>Crie sua conta</h1>
              <div className='input-field'>
                  <input type="name" placeholder='Nome' onChange={(e) => setName(e.target.value)} />
                  <FaUser className='icon' />
              </div>
              <div className='input-field'>
                  <input type="email" placeholder='E-mail' onChange={(e) => setEmail(e.target.value)} />
                  <FaEnvelope className='icon' />
              </div>
              <div className='input-field'>
                  <input type="password" placeholder='Senha' onChange={(e) => setPassword(e.target.value)} />
                  <FaLock className='icon' />
              </div>
              <div className='input-field'>
                  <input type="tel" placeholder='Telefone' onChange={(e) => setTelephone(e.target.value)} />
                  <FaPhone className='icon' />
              </div>
              <div className='input-field'>
                  <input type="date" placeholder='Data de nascimento' onChange={(e) => setBirthDate(e.target.value)} />
                  <FaBirthdayCake className='icon' />
              </div>

              <button>Registrar</button>
          </form>
      </div></>
  )
}

export default SignUp
