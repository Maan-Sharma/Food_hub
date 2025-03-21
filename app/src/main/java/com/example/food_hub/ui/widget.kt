package com.example.food_hub.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusTargetModifierNode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.food_hub.R
import com.example.food_hub.ui.theme.orange

@Composable
fun GroupSocalButton(color:Color=Color.White,
                     onFaceBookClick:()-> Unit,
                     onGoggleClick:()-> Unit)
{

    Column {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically)
        {
            HorizontalDivider(modifier = Modifier.weight(1f).padding(start = 8.dp), thickness = 1.dp, color =color)
            Text(text= stringResource(R.string.sign_in_with), color = color, modifier = Modifier.padding(8.dp))
            HorizontalDivider(modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
                ,thickness = 1.dp
                , color =color
            )
        }
        Row (modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween){
            SocialButton(
                icon = R.drawable.facebook,
                title = R.string.sign_up_facbook,
                onFaceBookClick
            )
            SocialButton(
                icon = R.drawable.goggle,
                title = R.string.sing_up_Google,
                onFaceBookClick
            )
        }
    }

}

@Composable
fun SocialButton(
    icon:Int,title:Int,onclick:()-> Unit
){
    Button(
        onClick = onclick,
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
    ) {
        Row(modifier = Modifier.height(38.dp),
            verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size( 8.dp))
            Text(text = stringResource(id=title), color = Color.Black )
        }

    }
}



@Composable
fun fooodhubTextfield(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = RoundedCornerShape(10.dp),
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors().copy(
        focusedIndicatorColor = orange,
        unfocusedIndicatorColor = Color.LightGray.copy(alpha = 1f)
    )

){
    Column(modifier=Modifier.padding(vertical = 8.dp)) {
        label?.let {
            Row {
                Spacer(modifier=Modifier.size(6.dp))
                it()
            }
        }
        Spacer(modifier=Modifier.size(8.dp))
      OutlinedTextField(value = value,
          onValueChange,
          modifier,
          enabled,
          readOnly,
          textStyle.copy(
              fontWeight = FontWeight.SemiBold
          ),
          null,
          placeholder,
          leadingIcon,
          trailingIcon,
          prefix,
          suffix,
          supportingText,
          isError,
          visualTransformation,
          keyboardOptions,
          keyboardActions,
          singleLine,
          maxLines,
          minLines,
          interactionSource,
          shape,
          colors)
    }
}




