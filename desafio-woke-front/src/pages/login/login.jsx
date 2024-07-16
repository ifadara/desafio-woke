import { FaLock, FaUser } from 'react-icons/fa';
import { useState } from "react";
import './styles.css'
import { useNavigate } from 'react-router';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'

const Login = () => {

    const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [loginStatus, setLoginStatus] = useState("")

    const handleSubmit = async (event) => {
        event.preventDefault();

        const response = await fetch('http://localhost:8080/v1/api/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            credentials: 'include',
            body: JSON.stringify({
                email,
                password
            })
        })


        const status = await response.status;

        setLoginStatus(status)

        if(status == 200){
            navigate('/home')
        }
        
    }

    function notify() {
        if(loginStatus == 200){
            toast.success("Sucesso")
        }
        else {
            toast.error("Usuário ou senha invalido")
        }
        ;
    };

    return (
        <><ToastContainer /><div className="container">
            <form onSubmit={handleSubmit}>
                <h1>Acesse o sistema</h1>
                <div className='input-field'>
                    <input type="email" placeholder='E-mail' onChange={(e) => setEmail(e.target.value)} />
                    <FaUser className='icon' />
                </div>
                <div className='input-field'>
                    <input type="password" placeholder='Senha' onChange={(e) => setPassword(e.target.value)} />
                    <FaLock className='icon' />
                </div>

                <div className="recall-forget">
                    <label>
                        <input type="checkbox" />
                        Lembrar Senha?
                    </label>
                </div>
                <button onClick={notify}>Entrar</button>

                <div className="signup-link">
                    <p>
                        Não tem uma conta? <a href="/sign-up">Registrar</a>
                    </p>
                </div>
            </form>
        </div></>
    )
}

export default Login
