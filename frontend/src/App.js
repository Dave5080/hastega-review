import { useState } from 'react';
import './App.css';
import Auth from './Authenticate';
import Overview from './BookOverview';

const App = () => {

    let [token, setToken] = useState('');

    if(token === '')
        return <Auth s={setToken}/>;
    else return (<Overview token={token} setToken={setToken} />)
}
export default App;