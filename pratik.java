import React, { useState, useEffect } from 'react';

const TodoApp = () => {
  const [todos, setTodos] = useState([]);
  const [task, setTask] = useState("");

  // Add a new task
  const addTask = (e) => {
    e.preventDefault();
    if (!task.trim()) return;
    const newTodo = { id: Date.now(), text: task, completed: false };
    setTodos([...todos, newTodo]);
    setTask("");
  };

  // Toggle completion
  const toggleComplete = (id) => {
    setTodos(todos.map(item => 
      item.id === id ? { ...item, completed: !item.completed } : item
    ));
  };

  // Delete task
  const deleteTask = (id) => {
    setTodos(todos.filter(item => item.id !== id));
  };

  return (
    <div style={styles.container}>
      <style>{`
        .todo-item { transition: all 0.3s ease; border-bottom: 1px solid #eee; }
        .todo-item:hover { background-color: #f9f9f9; }
        .completed { text-decoration: line-through; color: #888; }
        button:active { transform: scale(0.95); }
      `}</style>

      <div style={styles.card}>
        <h2 style={styles.title}>Daily Tasks</h2>
        
        <form onSubmit={addTask} style={styles.inputGroup}>
          <input 
            type="text" 
            placeholder="Add a new task..." 
            value={task}
            onChange={(e) => setTask(e.target.value)}
            style={styles.input}
          />
          <button type="submit" style={styles.addButton}>Add</button>
        </form>

        <div style={styles.list}>
          {todos.map((todo) => (
            <div key={todo.id} className="todo-item" style={styles.item}>
              <div 
                onClick={() => toggleComplete(todo.id)} 
                style={{ ...styles.text, cursor: 'pointer' }}
                className={todo.completed ? 'completed' : ''}
              >
                {todo.completed ? '✓ ' : '○ '} {todo.text}
              </div>
              <button 
                onClick={() => deleteTask(todo.id)} 
                style={styles.deleteButton}
              >
                Delete
              </button>
            </div>
          ))}
        </div>

        {todos.length === 0 && <p style={styles.empty}>No tasks yet. Start adding some!</p>}
      </div>
    </div>
  );
};

// Inline CSS Styles
const styles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    minHeight: '100vh',
    backgroundColor: '#f0f2f5',
    fontFamily: '"Segoe UI", Tahoma, Geneva, Verdana, sans-serif'
  },
  card: {
    background: '#fff',
    padding: '2rem',
    borderRadius: '12px',
    boxShadow: '0 10px 25px rgba(0,0,0,0.1)',
    width: '100%',
    maxWidth: '400px'
  },
  title: {
    margin: '0 0 1.5rem',
    color: '#333',
    textAlign: 'center'
  },
  inputGroup: {
    display: 'flex',
    gap: '10px',
    marginBottom: '1.5rem'
  },
  input: {
    flex: 1,
    padding: '10px',
    borderRadius: '6px',
    border: '1px solid #ddd',
    fontSize: '1rem'
  },
  addButton: {
    padding: '10px 20px',
    backgroundColor: '#007bff',
    color: 'white',
    border: 'none',
    borderRadius: '6px',
    cursor: 'pointer',
    fontWeight: '600'
  },
  item: {
    display: 'flex',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: '12px 5px'
  },
  text: {
    fontSize: '1.1rem',
    color: '#444'
  },
  deleteButton: {
    backgroundColor: 'transparent',
    color: '#ff4d4d',
    border: '1px solid #ff4d4d',
    padding: '4px 8px',
    borderRadius: '4px',
    cursor: 'pointer',
    fontSize: '0.8rem'
  },
  empty: {
    textAlign: 'center',
    color: '#999',
    marginTop: '1rem'
  }
};

export default TodoApp;