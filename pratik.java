import React, { useState } from 'react';

const TodoApp = () => {
  const [todos, setTodos] = useState([]);
  const [task, setTask] = useState("");

  // Add a new task
  const addTask = (e) => {
    e.preventDefault();
    if (!task.trim()) return;
    
    // Using a more robust ID than Date.now() if tasks are added extremely fast
    const newTodo = { id: crypto.randomUUID(), text: task, completed: false };
    setTodos([...todos, newTodo]);
    setTask("");
  };

  // Toggle completion
  const toggleComplete = (id) => {
    setTodos(prevTodos => 
      prevTodos.map(item => 
        item.id === id ? { ...item, completed: !item.completed } : item
      )
    );
  };

  // Delete task
  const deleteTask = (id) => {
    setTodos(prevTodos => prevTodos.filter(item => item.id !== id));
  };

  return (
    <div style={styles.container}>
      {/* Moved CSS to a single template literal for cleaner rendering */}
      <style>{`
        .todo-item { transition: all 0.2s ease; border-bottom: 1px solid #eee; }
        .todo-item:hover { background-color: #fcfcfc; }
        .completed-text { text-decoration: line-through; color: #aaa; }
        .todo-button:active { transform: scale(0.95); }
      `}</style>

      <div style={styles.card}>
        <h2 style={styles.title}>Daily Tasks</h2>
        
        <form onSubmit={addTask} style={styles.inputGroup}>
          <input 
            type="text" 
            placeholder="What needs to be done?" 
            value={task}
            onChange={(e) => setTask(e.target.value)}
            style={styles.input}
          />
          <button type="submit" className="todo-button" style={styles.addButton}>Add</button>
        </form>

        <div style={styles.list}>
          {todos.map((todo) => (
            <div key={todo.id} className="todo-item" style={styles.item}>
              <div 
                onClick={() => toggleComplete(todo.id)} 
                style={styles.textWrapper}
              >
                <span style={{ marginRight: '10px', color: todo.completed ? '#4caf50' : '#ccc' }}>
                  {todo.completed ? '●' : '○'}
                </span>
                <span className={todo.completed ? 'completed-text' : ''}>
                  {todo.text}
                </span>
              </div>
              <button 
                onClick={() => deleteTask(todo.id)} 
                className="todo-button"
                style={styles.deleteButton}
              >
                Delete
              </button>
            </div>
          ))}
        </div>

        {todos.length === 0 ? (
          <p style={styles.empty}>No tasks yet. Start adding some!</p>
        ) : (
          <div style={styles.footer}>
            {todos.filter(t => !t.completed).length} items left
          </div>
        )}
      </div>
    </div>
  );
};

// Styles updated for better layout
const styles = {
  container: {
    display: 'flex',
    justifyContent: 'center',
    paddingTop: '100px',
    minHeight: '100vh',
    backgroundColor: '#f4f7f6',
    fontFamily: '-apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif'
  },
  card: {
    background: '#fff',
    padding: '2rem',
    borderRadius: '16px',
    boxShadow: '0 20px 25px -5px rgba(0,0,0,0.1), 0 10px 10px -5px rgba(0,0,0,0.04)',
    width: '100%',
    maxWidth: '450px',
    height: 'fit-content'
  },
  title: { margin: '0 0 1.5rem', color: '#1a1a1a', textAlign: 'left', fontSize: '1.8rem', fontWeight: '800' },
  inputGroup: { display: 'flex', gap: '8px', marginBottom: '1.5rem' },
  input: { flex: 1, padding: '12px', borderRadius: '8px', border: '2px solid #eee', fontSize: '1rem', outline: 'none' },
  addButton: { padding: '10px 20px', backgroundColor: '#000', color: 'white', border: 'none', borderRadius: '8px', cursor: 'pointer', fontWeight: '600' },
  item: { display: 'flex', justifyContent: 'space-between', alignItems: 'center', padding: '14px 0' },
  textWrapper: { display: 'flex', alignItems: 'center', cursor: 'pointer', flex: 1, fontSize: '1.05rem' },
  deleteButton: { backgroundColor: '#fff', color: '#ff4d4f', border: '1px solid #ffccc7', padding: '5px 10px', borderRadius: '6px', cursor: 'pointer', fontSize: '0.75rem' },
  empty: { textAlign: 'center', color: '#aaa', marginTop: '2rem', fontStyle: 'italic' },
  footer: { marginTop: '1.5rem', paddingTop: '1rem', borderTop: '1px solid #eee', fontSize: '0.85rem', color: '#666' }
};

export default TodoApp;
