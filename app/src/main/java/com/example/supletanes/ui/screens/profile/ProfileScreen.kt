package com.example.supletanes.ui.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme.colorScheme

data class UserProfile(
    val name: String = "Juan Pérez",
    val email: String = "juan.perez@example.com"
)

@Composable
fun ProfileScreen(
    // ✅ 1. AÑADIMOS EL PARÁMETRO 'isGuest'
    isGuest: Boolean,
    // El parámetro 'user' ahora puede ser nulo si es un invitado
    user: UserProfile? = UserProfile(),
    onLogoutClicked: () -> Unit,
    onChangeNameClicked: () -> Unit = {},
    onChangePasswordClicked: () -> Unit = {},
    onPrivacyClicked: () -> Unit = {},
    // ✅ AÑADIMOS UNA ACCIÓN PARA NAVEGAR AL LOGIN DESDE EL MODO INVITADO
    onLoginClicked: () -> Unit = {}
) {
    // ✅ 2. USAMOS UNA CONDICIÓN PARA MOSTRAR LA UI ADECUADA
    if (isGuest) {
        // --- UI PARA EL MODO INVITADO ---
        GuestProfileScreen(onLoginClicked = onLoginClicked)
    } else {
        // --- UI PARA EL USUARIO LOGUEADO (tu código original) ---
        // Nos aseguramos de que 'user' no sea nulo, aunque en este flujo nunca lo será.
        user?.let {
            LoggedInProfileScreen(
                user = it,
                onLogoutClicked = onLogoutClicked,
                onChangeNameClicked = onChangeNameClicked,
                onChangePasswordClicked = onChangePasswordClicked,
                onPrivacyClicked = onPrivacyClicked
            )
        }
    }
}

// ✅ 3. EXTRAEMOS LA UI DEL USUARIO LOGUEADO A SU PROPIO COMPOSABLE
@Composable
private fun LoggedInProfileScreen(
    user: UserProfile,
    onLogoutClicked: () -> Unit,
    onChangeNameClicked: () -> Unit,
    onChangePasswordClicked: () -> Unit,
    onPrivacyClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = user.name,
            style = MaterialTheme.typography.headlineMedium,
            color = colorScheme.primary
        )
        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyLarge,
            color = colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(48.dp))
        Divider()

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Gestión de Cuenta",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )

        ProfileItem(
            icon = Icons.Default.Person,
            title = "Cambiar Nombre",
            onClick = onChangeNameClicked
        )
        ProfileItem(
            icon = Icons.Default.Key,
            title = "Cambiar Contraseña",
            onClick = onChangePasswordClicked
        )
        Divider()

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Opciones",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )

        ProfileItem(
            icon = Icons.Default.Lock,
            title = "Privacidad",
            onClick = onPrivacyClicked
        )
        Divider()

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onLogoutClicked,
            modifier = Modifier.fillMaxWidth(0.8f),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.error
            )
        ) {
            Icon(Icons.Default.Logout, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Cerrar Sesión", color = colorScheme.onError)
        }
    }
}

// ✅ 4. CREAMOS UN NUEVO COMPOSABLE PARA LA VISTA DE INVITADO
@Composable
private fun GuestProfileScreen(onLoginClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Modo Invitado",
            modifier = Modifier.size(80.dp),
            tint = colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Estás en modo invitado",
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Inicia sesión o crea una cuenta para gestionar tu perfil, ver tus planes y más.",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onLoginClicked,
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Iniciar Sesión / Registrarse")
        }
    }
}


// --- (El Composable ProfileItem no necesita cambios) ---
@Composable
fun ProfileItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    // ... tu código sin cambios
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = colorScheme.outline
        )
    }
}

// ✅ 5. ACTUALIZAMOS LOS PREVIEWS PARA PROBAR AMBOS ESTADOS
@Preview(name = "Logged In Preview", showBackground = true)
@Composable
fun LoggedInProfileScreenPreview() {
    ProfileScreen(isGuest = false, onLogoutClicked = {})
}

@Preview(name = "Guest Preview", showBackground = true)
@Composable
fun GuestProfileScreenPreview() {
    ProfileScreen(isGuest = true, onLogoutClicked = {})
}

