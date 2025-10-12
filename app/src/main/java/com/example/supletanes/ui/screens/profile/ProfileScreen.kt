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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme.colorScheme

data class UserProfile(
    val name: String = "Juan Pérez",
    val email: String = "juan.perez@example.com"
)

@Composable
fun ProfileScreen(
    user: UserProfile = UserProfile(),
    onLogoutClicked: () -> Unit,
    onChangeNameClicked: () -> Unit = {},
    onChangePasswordClicked: () -> Unit = {},
    onPrivacyClicked: () -> Unit = {}
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp)
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
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp)
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

@Composable
fun ProfileItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String, onClick: () -> Unit) {
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

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onLogoutClicked = {})
}