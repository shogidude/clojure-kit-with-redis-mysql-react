import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';

const HealthStatus = () => {
    const [healthData, setHealthData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch('/api/health')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                setHealthData(data);
                setLoading(false);
            })
            .catch(error => {
                setError(error);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <div className="health-status">
            <img src={logo} className="App-logo" alt="logo" style={{width: '50px', height: '50px'}}/>
            <h2>Health Status</h2>
            <p><strong>Time:</strong> {healthData.time}</p>
            <p><strong>Up Since:</strong> {healthData['up-since']}</p>
            <p><strong>Clojure Kit Server:</strong> {healthData['clojure-kit-server']}</p>
            <p><strong>Redis Connected:</strong> {healthData['redis-connected'] ? 'Yes' : 'No'}</p>
            <p><strong>MySQL Connected:</strong> {healthData['mysql-connected'] ? 'Yes' : 'No'}</p>
            <p><strong>API Endpoints Working:</strong> Yes</p>
            <p><strong>React Working:</strong> Yes</p>
        </div>
    );
};

export default HealthStatus;
