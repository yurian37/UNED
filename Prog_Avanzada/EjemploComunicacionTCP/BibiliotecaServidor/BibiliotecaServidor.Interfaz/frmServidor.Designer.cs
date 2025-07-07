namespace BibiliotecaServidor.Interfaz
{
    partial class frmServidor
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            groupBox1 = new System.Windows.Forms.GroupBox();
            btnDetener = new System.Windows.Forms.Button();
            btnIniciar = new System.Windows.Forms.Button();
            lblEstado = new System.Windows.Forms.Label();
            label1 = new System.Windows.Forms.Label();
            groupBox2 = new System.Windows.Forms.GroupBox();
            lstClientesConectados = new System.Windows.Forms.ListBox();
            groupBox3 = new System.Windows.Forms.GroupBox();
            txtBitacora = new System.Windows.Forms.TextBox();
            btnMantenimiento = new System.Windows.Forms.Button();
            groupBox1.SuspendLayout();
            groupBox2.SuspendLayout();
            groupBox3.SuspendLayout();
            SuspendLayout();
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(btnMantenimiento);
            groupBox1.Controls.Add(btnDetener);
            groupBox1.Controls.Add(btnIniciar);
            groupBox1.Controls.Add(lblEstado);
            groupBox1.Controls.Add(label1);
            groupBox1.Location = new System.Drawing.Point(16, 18);
            groupBox1.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            groupBox1.Name = "groupBox1";
            groupBox1.Padding = new System.Windows.Forms.Padding(4, 5, 4, 5);
            groupBox1.Size = new System.Drawing.Size(761, 191);
            groupBox1.TabIndex = 1;
            groupBox1.TabStop = false;
            groupBox1.Text = "Información del servidor";
            // 
            // btnDetener
            // 
            btnDetener.Location = new System.Drawing.Point(152, 131);
            btnDetener.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            btnDetener.Name = "btnDetener";
            btnDetener.Size = new System.Drawing.Size(120, 35);
            btnDetener.TabIndex = 3;
            btnDetener.Text = "Detener";
            btnDetener.UseVisualStyleBackColor = true;
            btnDetener.Click += btnDetener_Click;
            // 
            // btnIniciar
            // 
            btnIniciar.Location = new System.Drawing.Point(13, 131);
            btnIniciar.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            btnIniciar.Name = "btnIniciar";
            btnIniciar.Size = new System.Drawing.Size(112, 35);
            btnIniciar.TabIndex = 2;
            btnIniciar.Text = "Iniciar";
            btnIniciar.UseVisualStyleBackColor = true;
            btnIniciar.Click += btnIniciar_Click;
            // 
            // lblEstado
            // 
            lblEstado.AutoSize = true;
            lblEstado.Location = new System.Drawing.Point(84, 49);
            lblEstado.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            lblEstado.Name = "lblEstado";
            lblEstado.Size = new System.Drawing.Size(73, 20);
            lblEstado.TabIndex = 1;
            lblEstado.Text = "Sin iniciar";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 8.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point);
            label1.Location = new System.Drawing.Point(9, 49);
            label1.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label1.Name = "label1";
            label1.Size = new System.Drawing.Size(63, 17);
            label1.TabIndex = 0;
            label1.Text = "Estado:";
            // 
            // groupBox2
            // 
            groupBox2.Controls.Add(lstClientesConectados);
            groupBox2.Location = new System.Drawing.Point(16, 249);
            groupBox2.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            groupBox2.Name = "groupBox2";
            groupBox2.Padding = new System.Windows.Forms.Padding(4, 5, 4, 5);
            groupBox2.Size = new System.Drawing.Size(384, 549);
            groupBox2.TabIndex = 2;
            groupBox2.TabStop = false;
            groupBox2.Text = "Clientes conectados";
            // 
            // lstClientesConectados
            // 
            lstClientesConectados.FormattingEnabled = true;
            lstClientesConectados.ItemHeight = 20;
            lstClientesConectados.Location = new System.Drawing.Point(8, 29);
            lstClientesConectados.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            lstClientesConectados.Name = "lstClientesConectados";
            lstClientesConectados.Size = new System.Drawing.Size(367, 504);
            lstClientesConectados.TabIndex = 0;
            // 
            // groupBox3
            // 
            groupBox3.Controls.Add(txtBitacora);
            groupBox3.Location = new System.Drawing.Point(408, 249);
            groupBox3.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            groupBox3.Name = "groupBox3";
            groupBox3.Padding = new System.Windows.Forms.Padding(4, 5, 4, 5);
            groupBox3.Size = new System.Drawing.Size(369, 549);
            groupBox3.TabIndex = 3;
            groupBox3.TabStop = false;
            groupBox3.Text = "Bítacora";
            // 
            // txtBitacora
            // 
            txtBitacora.Location = new System.Drawing.Point(8, 29);
            txtBitacora.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            txtBitacora.Multiline = true;
            txtBitacora.Name = "txtBitacora";
            txtBitacora.ScrollBars = System.Windows.Forms.ScrollBars.Vertical;
            txtBitacora.Size = new System.Drawing.Size(352, 504);
            txtBitacora.TabIndex = 0;
            // 
            // btnMantenimiento
            // 
            btnMantenimiento.Location = new System.Drawing.Point(617, 131);
            btnMantenimiento.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            btnMantenimiento.Name = "btnMantenimiento";
            btnMantenimiento.Size = new System.Drawing.Size(120, 35);
            btnMantenimiento.TabIndex = 4;
            btnMantenimiento.Text = "Autores";
            btnMantenimiento.UseVisualStyleBackColor = true;
            btnMantenimiento.Click += btnMantenimiento_Click;
            // 
            // frmServidor
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(795, 838);
            Controls.Add(groupBox3);
            Controls.Add(groupBox2);
            Controls.Add(groupBox1);
            Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            Name = "frmServidor";
            Text = "Servidor";
            groupBox1.ResumeLayout(false);
            groupBox1.PerformLayout();
            groupBox2.ResumeLayout(false);
            groupBox3.ResumeLayout(false);
            groupBox3.PerformLayout();
            ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Button btnDetener;
        private System.Windows.Forms.Button btnIniciar;
        private System.Windows.Forms.Label lblEstado;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.ListBox lstClientesConectados;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.TextBox txtBitacora;
        private System.Windows.Forms.Button btnMantenimiento;
    }
}

