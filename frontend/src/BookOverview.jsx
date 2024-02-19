import { useEffect, useState } from 'react';
import NewBookRow from './NewBook';
import BookRow from './BookRow';

const Overview = ({ token, setToken }) => {

    const [books, setBooks] = useState([]);
    const [newBooks, setNewBooks] = useState([]);

    useEffect(() => {
      fetch("http://localhost:8080/books?Authorization="+token, {
        method: "GET",
        headers: {'Authorization': token, 'Content-Type': 'application/json'},
      }).then(response => response.json())
        .then(data=> data.map(book => ({...book, ISBN: book.bookId.isbn})))
        .then(data => {
          const newData = data.map(book => ({...book, view: true}));
          setBooks(newData);
      }).catch((e) => setToken(''))
    },[]);

    function deleteBook(ISBN) {
      console.log(ISBN);
        fetch("http://localhost:8080/delete-book?Authorization="+token+"&ISBN="+ISBN,{
          method: "DELETE",
          headers: {'Authorization': token}
        }).then(() => {
          setBooks((oldValues) => {
            return oldValues.filter((book) => book.ISBN !== ISBN);
          });
        }).catch((e) => setToken(''));
      }
    
      function editModeChange(ISBN) {
        console.log("Changing edit mode");
        for (const element of books) {
          if (element.ISBN === ISBN) {
            element["view"] = !element["view"];
          }
        }
        setBooks((prevUsers) => [...prevUsers]);
      }
    
      function updateBook(ISBN, book) {
        console.log(ISBN);
        console.log(book);
        fetch("http://localhost:8080/edit-book?Authorization="+token+"&ISBN="+ISBN, {
          method: "POST",
          headers: {'Authorization': token, 'Content-Type': 'application/json'},
          body: JSON.stringify(book),
        }).then(() => {
          for (const element of books)
            if (element.ISBN === ISBN) {
              for(const p in element)
                element[p] = book[p];
              editModeChange(element.ISBN);
              break;
            }
        }).catch((e) => setToken(''));
      }
    
      function addNewBookInput() {
        setNewBooks((newBooks) => [...newBooks, {}]);
      }
    
      function deleteNewBook(ISBN) {
        setNewBooks((oldValues) => {
          return oldValues.filter((book) => book.ISBN !== ISBN);
        });
      }
    
      function updateNewBook(newBook) {
        newBook["view"] = true;
        setBooks([...books, newBook]);
      }

    return (
        <div className='container'>
            {books.map((book) => (
            <div key={book.ISBN}>
                <BookRow 
                    book={book}
                    deleteBook={deleteBook}
                    editModeChange={editModeChange}
                    updateBook={updateBook}
                />
            </div>
            ))}
        
            {newBooks.map((newBook) => (
            <div>
                <NewBookRow 
                    newBook={newBook}
                    deleteNewBook={deleteNewBook}
                    updateNewBook={updateNewBook}
                    token={token}
                />
            </div>
            ))}
            <div>
                <button onClick={() => addNewBookInput()}>New Book</button>
            </div>
        </div>
    );
}
export default Overview;
