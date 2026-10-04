package com.mycompany.registroalumnos;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Lógica de la ventana de registro. La interfaz (componentes) vive en
 * {@link VentanaRegistroFrame}; aquí se valida, se crea el alumno y se
 * actualiza la lista.
 */
public class VentanaRegistro {

    private static final String PATRON_EMAIL = "^[\\w.+-]+@[\\w-]+\\.[\\w.]+$";
    private static final int EDAD_POR_DEFECTO = 18;
    private static final int EDAD_MINIMA = 16;

    private final VentanaRegistroFrame ui;
    private final DefaultListModel<String> modeloLista = new DefaultListModel<>();

    public VentanaRegistro(VentanaRegistroFrame ui) {
        this.ui = ui;
        ui.lstAlumnos.setModel(modeloLista);
        ui.sldEdad.addChangeListener(e -> actualizarEtiquetaEdad());
        limpiar();
    }

    /** Valida el formulario y, si es correcto, añade el alumno a la lista. */
    public void guardar() {
        if (!validar()) {
            return;
        }
        modeloLista.addElement(crearAlumno().toString());
        limpiar();
    }

    /** Deja el formulario en su estado inicial. */
    public void limpiar() {
        ui.txtNombre.setText("");
        ui.txtEmail.setText("");
        ui.rbOtro.setSelected(true);
        ui.cmbCurso.setSelectedIndex(0);
        ui.sldEdad.setValue(EDAD_POR_DEFECTO);
        ui.chkCondiciones.setSelected(false);
        actualizarEtiquetaEdad();
        ui.txtNombre.requestFocus();
    }

    private boolean validar() {
        if (ui.txtNombre.getText().isBlank()) {
            mostrarError("El nombre es obligatorio.");
            ui.txtNombre.requestFocus();
            return false;
        }
        if (!ui.txtEmail.getText().trim().matches(PATRON_EMAIL)) {
            mostrarError("El email no es válido.");
            ui.txtEmail.requestFocus();
            return false;
        }
        if (ui.sldEdad.getValue() < EDAD_MINIMA) {
            mostrarError("La edad mínima es " + EDAD_MINIMA + " años.");
            return false;
        }
        if (!ui.chkCondiciones.isSelected()) {
            mostrarError("Debes aceptar las condiciones.");
            return false;
        }
        return true;
    }

    private Alumno crearAlumno() {
        return new Alumno(
                ui.txtNombre.getText().trim(),
                ui.txtEmail.getText().trim(),
                obtenerGenero(),
                (String) ui.cmbCurso.getSelectedItem(),
                ui.sldEdad.getValue());
    }

    private String obtenerGenero() {
        if (ui.rbHombre.isSelected()) {
            return "Hombre";
        }
        if (ui.rbMujer.isSelected()) {
            return "Mujer";
        }
        return "Otro";
    }

    private void actualizarEtiquetaEdad() {
        ui.lblEdad.setText("Edad: " + ui.sldEdad.getValue());
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(ui, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
