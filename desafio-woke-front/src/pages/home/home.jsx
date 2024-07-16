import React, { useEffect, useState } from 'react';
import './styles.css';
import { useNavigate } from 'react-router';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css'

const jobListings = [
  { id: 1, title: 'Frontend Developer', company: 'Tech Co.', location: 'Remote' },
  { id: 2, title: 'Backend Developer', company: 'Code Inc.', location: 'New York' },
  { id: 3, title: 'Fullstack Developer', company: 'Dev Solutions', location: 'San Francisco' },
];

function Home() {

  const navigate = useNavigate();
  const [id, setId] = useState("")
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [telephone, setTelephone] = useState("");
  const [birthDate, setBirthDate] = useState("");

  const[pageStatus, setPageStatus] = useState("")

  useEffect(() => {
    (
      async () => {
        const response = await fetch('http://localhost:8080/v1/api/auth/authenticate', {
          headers: { 'Content-Type': 'application/json' },
          credentials: 'include'
        });

      const content = await response.json();
      const status = await response.status;

      if(status == 401){
        navigate('/login')
      }

      

      setId(content.id)
      setName(content.name)
      setEmail(content.email)
      setTelephone(content.telephone)
      setBirthDate(content.setBirthDate)

      }
    )()
  
  
})

function notify() {
  if(pageStatus == 201){
      toast.success("Usuário aplicou para vaga com sucesso")
  }
  ;
};

const logout = async (event) => {
  event.preventDefault();

  await fetch('http://localhost:8080/v1/api/auth/logout', {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      credentials: "include"
  })

  navigate('/login')
}

const apply = async (event) => {
  event.preventDefault();

  const response = await fetch(`http://localhost:8080/api/user/${id}`, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      credentials: "include"
  })

  const status = await response.status

  notify()

  setPageStatus(status)
}

  return (
    <><ToastContainer /><div className="home-container">
      <div className="dashboard">
        <h2>Área Restrita do usuário {name}</h2>
        <div className="user-info">
          <p><strong>Name:</strong> {name}</p>
          <p><strong>Email:</strong> {email}</p>
          <p><strong>Phone:</strong> {telephone}</p>
          <p><strong>Date of Birth:</strong> {birthDate}</p>
          <button className="logout-button" onClick={logout}>Log Out</button>
        </div>
      </div>
      <div className="job-listings">
        <h2>Job Listings</h2>
        <div className="cards-container">
          {jobListings.map(job => (
            <div key={job.id} className="job-card">
              <h3>{job.title}</h3>
              <p><strong>Company:</strong> {job.company}</p>
              <p><strong>Location:</strong> {job.location}</p>
              <button onClick={apply}>Apply Now</button>
            </div>
          ))}
        </div>
      </div>
    </div></>
  );
}

export default Home;
