import { useState } from 'react';
const Auth = ({ s }) => {

    const [login, setlogin] = useState(true);

    const [loginFailed, setLoginFailed] = useState(false);
    const [registerFailed, setRegisterFailed] = useState(false);

    const handleLogin = (event) => {
        event.preventDefault();
        let user = {
            email: event.target.email.value, 
            password: event.target.password.value
        };
        fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: new Headers({'content-type': 'application/json'}),
            body: JSON.stringify(user)
        }).then(response => {
            if(response.ok) return response.text();
            else throw new Error(response.status)
        }).then(data => s(data))
            .catch(error => setLoginFailed(true));
    }
    
    const handleRegister = (event) => {
        event.preventDefault();
        let user = {
            nome: event.target.nome.value, 
            cognome: event.target.cognome.value, 
            email: event.target.email.value, 
            password: event.target.password.value
        };
        fetch('http://localhost:8080/register', {
            method: 'POST',
            headers: new Headers({'content-type': 'application/json'}),
            body: JSON.stringify(user)
        }).then(response => response.json())
            .then(data => console.log(data))
            .then(setlogin(true))
            .catch(setRegisterFailed(true));
    }

    if(!login)
        return (
            <div className="container">
                <h1>Registrati</h1>
                <form onSubmit={handleRegister}>
                    <input type="text" name="nome" placeholder="Nome utente" required/>
                    <input type="text" name="cognome" placeholder="Cognome utente" required/>
                    <input type="email" name="email" placeholder="Email" required/>
                    <input type="password" name="password" placeholder="Password" required/>
                    <input type="password" name="confirm-password" placeholder="Conferma Password" required/>
                    <input type="submit" value="Registrati"/>
                </form>
                <p>Hai già un account? <a href="/" onClick={(e) => {e.preventDefault(); setlogin(true)}}>Accedi qui</a></p>
            </div>
        );
    else return (
        <div className="container">
            <h1>Accedi</h1>
            <form onSubmit={handleLogin}>
                <input type="email" name="email" placeholder="Email" required/>
                <input type="password" name="password" placeholder="Password" required/>
                <input type="submit" value="Accedi"/>
            </form>
            <p>Non hai un account? <a href="/" onClick={(e) => {e.preventDefault(); setlogin(false)}}>Registrati ora</a></p>
        </div>
        );
}

export default Auth;