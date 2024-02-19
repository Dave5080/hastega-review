import React from "react";
import { useState } from "react";
import "./App.css";

export default function BookRow(probs) {
  const [ISBN, setISBN] = useState(probs.book.ISBN);
  const [titolo, setTitolo] = useState(probs.book.titolo);
  const [autore, setAutore] = useState(probs.book.autore);
  const [trama, setTrama] = useState(probs.book.trama);
  const [letture, setLetture] = useState(probs.book.letture);

  const [oldISBN, setOldISBN] = useState(probs.book.ISBN);

  if (probs.book.view) {
    return (
      <div className="Book">
        <p style={{ margin: 20 }}>{ISBN}</p>
        <p style={{ margin: 20 }}>{titolo}</p>
        <p style={{ margin: 20 }}>{autore}</p>
        <p style={{ margin: 20 }}>{trama}</p>
        <p style={{ margin: 20 }}>{letture}</p>
        <button onClick={() => probs.deleteBook(probs.book.ISBN)}>Delete</button>
        <button onClick={() => {setOldISBN(ISBN); probs.editModeChange(probs.book.ISBN);
                                }}>Edit</button>
      </div>
    );
  } else {
    return (
      <div className="Book">
        <div className="NewBook">
          <div className="InputBox">
            <label>ISBN:</label>
            <input
              type="text"
              name="ISBN"
              value={ISBN}
              onChange={(event) => setISBN(event.target.value)}
            />
          </div>
          <div className="InputBox">
            <label>Titolo:</label>
            <input
              type="text"
              name="titolo"
              value={titolo}
              onChange={(event) => setTitolo(event.target.value)}
            />
          </div>
          <div className="InputBox">
            <label>Autore:</label>
            <input
              type="text"
              name="autore"
              value={autore}
              onChange={(event) => setAutore(event.target.value)}
            />
          </div>
          <div className="InputBox">
            <label>Trama:</label>
            <input
              type="text"
              name="trama"
              value={trama}
              onChange={(event) => setTrama(event.target.value)}
            />
          </div>
          <div className="InputBox">
            <label>Letture:</label>
            <input
              type="number"
              name="letture"
              value={letture}
              min="0"
              onChange={(event) => setLetture(event.target.value)}
            />
          </div>
        </div>
        <button onClick={() => {probs.updateBook(oldISBN, {ISBN: ISBN, titolo: titolo, autore: autore, trama: trama, letture: letture});
                                  probs.editModeChange(probs.book.ISBN);}}>
          Update
        </button>
        <button onClick={() => probs.editModeChange(probs.book.ISBN)}>
          Restore
        </button>
      </div>
    );
  }
}