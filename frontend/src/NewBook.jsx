import React from "react";
import { useState } from "react";
import "./App.css";

export default function NewBookRow(probs) {
  const [ISBN, setISBN] = useState("");
  const [titolo, setTitolo] = useState("");
  const [autore, setAutore] = useState("");
  const [trama, setTrama] = useState("");
  const [letture, setLetture] = useState(0);

  const [e403, setE403] = useState(false);

  const onSubmit = (e) => {
    e.preventDefault();
    setE403(false);

    let newBook = {
      ISBN: ISBN,
      titolo: titolo,
      autore: autore,
      trama: trama,
      letture: letture
    };

    fetch("http://localhost:8080/add-book?Authorization="+probs.token, {
      method: "POST",
      headers: {'Authorization': probs.token, 'Content-Type': 'application/json'},
      body: JSON.stringify(newBook),
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        probs.deleteNewBook(probs.newBook.ISBN);
        probs.updateNewBook(newBook);
      })
      .catch((e) => {
        console.log("Somthing failed");
      });
  };

  return (
  <div>
      { e403 
      ? <p>ERRORE 403 rifai il login!</p>
      : <p></p> 
      }
    
    <form onSubmit={onSubmit}>
      <div className="NewBook">
        <div className="InputBox">
          <label>ISBN:</label>
          <input
            type="text"
            name="ISBN"
            onChange={(event) => setISBN(event.target.value)}
          />
        </div>
        <div className="InputBox">
          <label>Titolo:</label>
          <input
            type="text"
            name="titolo"
            onChange={(event) => setTitolo(event.target.value)}
          />
        </div>
        <div className="InputBox">
          <label>Autore:</label>
          <input
            type="text"
            name="autore"
            onChange={(event) => setAutore(event.target.value)}
          />
        </div>
        <div className="InputBox">
          <label>Trama:</label>
          <input
            type="text"
            name="trama"
            onChange={(event) => setTrama(event.target.value)}
          />
        </div>
        <div className="InputBox">
          <label>Letture:</label>
          <input
            type="number"
            name="letture"
            min="0"
            onChange={(event) => setLetture(event.target.value)}
          />
        </div>
        <button type="submit">Submit</button>
        <button onClick={() => probs.deleteNewBook(probs.id)}>Delete</button>
      </div>
    </form>
  </div>
  );
}