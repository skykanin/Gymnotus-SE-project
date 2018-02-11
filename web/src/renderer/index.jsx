import React from "react";
import ReactDOM from "react-dom";

import { Welcome } from "./components/Welcome/Welcome"
import { Header } from "./components/Header/Header"
import { Button } from "./components/Button/Button"

const App = () => (
    <div>
        <Header />
        <Welcome compiler="Javascript" framework="React" />
        <Button />
    </div>

);

ReactDOM.render(
    <App/>,
    document.getElementById("app")
);