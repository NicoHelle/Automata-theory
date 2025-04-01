
# Equation State Machine Tool

This Java-based application provides a graphical and programmatic interface to analyze binary word patterns using state machines. It allows users to calculate word equations, apply filters, detect redundancies, visualize state transitions, and interactively explore results via a GUI.

---

Features

- Calculate equations between binary words using custom finite state machines
- Graphical user interface with:
  - Word generation & filtering
  - Equation comparison
  - Redundancy removal
  - Real-time visualization of state machines
- Supports various filtering methods:
  - Contains / not contains substring
  - Substring in a specific position range
- Rewrite words for easier visualization (e.g., "aaa" → B, "bab" → 1)
- Save output to `.txt` files
- Supports transitivity and concatenation reduction

---

## Main Components

- **Main.java** — CLI-based interface (legacy, replaced by GUI)
- **GUI.java** — Swing-based interface to interact with all functionalities
- **Equation.java** — Core logic for generating, comparing, and analyzing equations
- **Coordinates.java** — Stores coordinate and word metadata
- **Filter.java** — Tools for word filtering and rewriting
- **Redundancy.java** — Handles transitivity and duplication cleanup
- **DisplayStateMachine.java** — Displays image representations of state machines
- **SaveFile.java** — Saves content from GUI into a text file
- **NotImplemented.java** — Contains experimental or debug-only logic

---

## How to Run

1. Open the project in your preferred Java IDE (e.g., IntelliJ, Eclipse).
2. Compile the files.
3. Run the application from `GUI.java` or `Main.java` (for terminal usage).

To launch GUI directly:

```bash
javac *.java
java GUI
```

---

## Requirements

- Java JDK 8 or newer
- Swing (part of standard JDK)
- Image files (e.g., `A1.PNG`, `A2.PNG`) in the project root for visualization

---

## Example Use Cases

- Generate all binary strings of a given length.
- Determine if two words are mathematically "equal" based on state transitions.
- Filter equations based on substrings.
- Visualize state transitions and save filtered results.

---

## License

This project is provided for academic and research use only.

---

## Author
**Nico Helle**  


---
