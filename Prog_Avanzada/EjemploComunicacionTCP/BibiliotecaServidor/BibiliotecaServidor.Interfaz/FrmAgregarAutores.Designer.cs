namespace BibiliotecaServidor.Interfaz
{
    partial class FrmAgregarAutores
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            groupBox1 = new System.Windows.Forms.GroupBox();
            label3 = new System.Windows.Forms.Label();
            label2 = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            Identificador = new System.Windows.Forms.Label();
            txtApe2 = new System.Windows.Forms.TextBox();
            txtNombre = new System.Windows.Forms.TextBox();
            txtApe1 = new System.Windows.Forms.TextBox();
            txtId = new System.Windows.Forms.TextBox();
            btnGuardar = new System.Windows.Forms.Button();
            grdListaAutores = new System.Windows.Forms.DataGridView();
            IdAutor = new System.Windows.Forms.DataGridViewTextBoxColumn();
            Nombre = new System.Windows.Forms.DataGridViewTextBoxColumn();
            PrimerApellido = new System.Windows.Forms.DataGridViewTextBoxColumn();
            SegundoApellido = new System.Windows.Forms.DataGridViewTextBoxColumn();
            groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)grdListaAutores).BeginInit();
            SuspendLayout();
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(label3);
            groupBox1.Controls.Add(label2);
            groupBox1.Controls.Add(label1);
            groupBox1.Controls.Add(Identificador);
            groupBox1.Controls.Add(txtApe2);
            groupBox1.Controls.Add(txtNombre);
            groupBox1.Controls.Add(txtApe1);
            groupBox1.Controls.Add(txtId);
            groupBox1.Controls.Add(btnGuardar);
            groupBox1.Location = new System.Drawing.Point(23, 24);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new System.Drawing.Size(588, 151);
            groupBox1.TabIndex = 0;
            groupBox1.TabStop = false;
            groupBox1.Text = "Información de Autores";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new System.Drawing.Point(282, 80);
            label3.Name = "label3";
            label3.Size = new System.Drawing.Size(129, 20);
            label3.TabIndex = 8;
            label3.Text = "Segundo Apellido";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new System.Drawing.Point(282, 27);
            label2.Name = "label2";
            label2.Size = new System.Drawing.Size(64, 20);
            label2.TabIndex = 7;
            label2.Text = "Nombre";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new System.Drawing.Point(14, 82);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(113, 20);
            label1.TabIndex = 6;
            label1.Text = "Primer Apellido";
            // 
            // Identificador
            // 
            Identificador.AutoSize = true;
            Identificador.Location = new System.Drawing.Point(14, 27);
            Identificador.Name = "Identificador";
            Identificador.Size = new System.Drawing.Size(94, 20);
            Identificador.TabIndex = 5;
            Identificador.Text = "Identificador";
            // 
            // txtApe2
            // 
            txtApe2.Location = new System.Drawing.Point(286, 103);
            txtApe2.Name = "txtApe2";
            txtApe2.Size = new System.Drawing.Size(125, 27);
            txtApe2.TabIndex = 4;
            // 
            // txtNombre
            // 
            txtNombre.Location = new System.Drawing.Point(286, 50);
            txtNombre.Name = "txtNombre";
            txtNombre.Size = new System.Drawing.Size(125, 27);
            txtNombre.TabIndex = 3;
            // 
            // txtApe1
            // 
            txtApe1.Location = new System.Drawing.Point(29, 105);
            txtApe1.Name = "txtApe1";
            txtApe1.Size = new System.Drawing.Size(125, 27);
            txtApe1.TabIndex = 2;
            // 
            // txtId
            // 
            txtId.Location = new System.Drawing.Point(29, 50);
            txtId.Name = "txtId";
            txtId.Size = new System.Drawing.Size(125, 27);
            txtId.TabIndex = 1;
            // 
            // btnGuardar
            // 
            btnGuardar.Location = new System.Drawing.Point(479, 103);
            btnGuardar.Name = "btnGuardar";
            btnGuardar.Size = new System.Drawing.Size(94, 29);
            btnGuardar.TabIndex = 0;
            btnGuardar.Text = "Guardar";
            btnGuardar.UseVisualStyleBackColor = true;
            btnGuardar.Click += btnGuardar_Click;
            // 
            // grdListaAutores
            // 
            grdListaAutores.AllowUserToAddRows = false;
            grdListaAutores.AllowUserToDeleteRows = false;
            grdListaAutores.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            grdListaAutores.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] { IdAutor, Nombre, PrimerApellido, SegundoApellido });
            grdListaAutores.Location = new System.Drawing.Point(23, 201);
            grdListaAutores.Name = "grdListaAutores";
            grdListaAutores.ReadOnly = true;
            grdListaAutores.RowHeadersWidth = 51;
            grdListaAutores.RowTemplate.Height = 29;
            grdListaAutores.Size = new System.Drawing.Size(588, 188);
            grdListaAutores.TabIndex = 1;
            // 
            // IdAutor
            // 
            IdAutor.DataPropertyName = "IdAutor";
            IdAutor.HeaderText = "Identificación";
            IdAutor.MinimumWidth = 6;
            IdAutor.Name = "IdAutor";
            IdAutor.ReadOnly = true;
            IdAutor.Width = 125;
            // 
            // Nombre
            // 
            Nombre.DataPropertyName = "Nombre";
            Nombre.HeaderText = "Nombre";
            Nombre.MinimumWidth = 6;
            Nombre.Name = "Nombre";
            Nombre.ReadOnly = true;
            Nombre.Width = 125;
            // 
            // PrimerApellido
            // 
            PrimerApellido.DataPropertyName = "PrimerApellido";
            PrimerApellido.HeaderText = "Primer Apellido";
            PrimerApellido.MinimumWidth = 6;
            PrimerApellido.Name = "PrimerApellido";
            PrimerApellido.ReadOnly = true;
            PrimerApellido.Width = 125;
            // 
            // SegundoApellido
            // 
            SegundoApellido.DataPropertyName = "PrimerApellido";
            SegundoApellido.HeaderText = "Segundo Apellido";
            SegundoApellido.MinimumWidth = 6;
            SegundoApellido.Name = "SegundoApellido";
            SegundoApellido.ReadOnly = true;
            SegundoApellido.Width = 125;
            // 
            // FrmAgregarAutores
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(653, 418);
            Controls.Add(grdListaAutores);
            Controls.Add(groupBox1);
            Name = "FrmAgregarAutores";
            Text = "FrmAgregarAutores";
            Load += FrmAgregarAutores_Load;
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)grdListaAutores).EndInit();
            ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.TextBox txtApe2;
        private System.Windows.Forms.TextBox txtNombre;
        private System.Windows.Forms.TextBox txtApe1;
        private System.Windows.Forms.TextBox txtId;
        private System.Windows.Forms.Button btnGuardar;
        private System.Windows.Forms.DataGridView grdListaAutores;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label Identificador;
        private System.Windows.Forms.DataGridViewTextBoxColumn IdAutor;
        private System.Windows.Forms.DataGridViewTextBoxColumn Nombre;
        private System.Windows.Forms.DataGridViewTextBoxColumn PrimerApellido;
        private System.Windows.Forms.DataGridViewTextBoxColumn SegundoApellido;
    }
}