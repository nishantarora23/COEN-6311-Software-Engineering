import React, { useState } from "react";
import { injectIntl } from "react-intl";
import { Button, Grid, TextField } from "@mui/material";
import { Controller, useForm } from "react-hook-form";
import "./Details.scss";
import axios from "axios";
import LoadingSpinner from "../../common/LoadingSpinner/LoadingSpinner";
import AppSnackbar from "../../AppSnackbar/AppSnackbar";
import { API_URL } from "../../../constants";
import { getUserId } from "../../../services/userInfoService";

type Props = {
  intl: any;
  formData: any; // Update the type according to your formData structure
  setFormData: React.Dispatch<React.SetStateAction<any>>; // Update the type accordingly
};

const SkillsAndJobsForm = ({ formData, setFormData, intl }: Props) => {
  const { control, handleSubmit } = useForm({
    defaultValues: {
      skills: formData.skills,
      location: formData.location,
    },
  });
  const [loading, setLoading] = useState(false);
  const [isLoginError, setLoginError] = useState(false);
  const userId = getUserId();
  const onSubmit = async (data: any) => {
  const additionalData = {
      id: userId
  };
      setFormData({ ...formData, ...data });  
      // Define the API endpoint
      const endpoint = `${API_URL}/skills`;     
      try {
        const response = await fetch(endpoint, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({ ...formData, ...data, ...additionalData }),
        });    
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        // Handle the response data
        const result = await response.json();
        <AppSnackbar
        type="success"
        message={intl.formatMessage({
          id: 'skillsForm.submit.success'
        })}
        open={true}
      />
      } catch (error) {
        console.error('There was an error!', error);
        <AppSnackbar
        type="error"
        message={intl.formatMessage({
          id: 'skillsForm.submit.success'
        })}
        open={true}
      />
      }
  };

  return (
    <Grid container className="form-card" spacing={2}>
      <Grid item xs={12}>
        <Controller
          name="skills"
          control={control}
          rules={{ required: true }}
          render={({ field, fieldState: { error } }) => (
            <TextField
              {...field}
              fullWidth
              variant="outlined"
              label={intl.formatMessage({
                id: "skillsForm.label.skills",
              })}
              error={!!error}
              helperText={
                error ? intl.formatMessage({ id: "skillsForm.error.required" }) : ""
              }
            />
          )}
        />
      </Grid>

      <Grid item xs={12}>
        <Controller
          name="location"
          control={control}
          rules={{ required: true }}
          render={({ field, fieldState: { error } }) => (
            <TextField
              {...field}
              fullWidth
              variant="outlined"
              label={intl.formatMessage({
                id: "skillsForm.label.location",
              })}
              error={!!error}
              helperText={
                error ? intl.formatMessage({ id: "skillsForm.error.required" }) : ""
              }
            />
          )}
        />
      </Grid>

      <Grid item xs={12}>
        <Button
          variant="contained"
          fullWidth
          onClick={handleSubmit(onSubmit)}
        >
          {intl.formatMessage({
            id: "skillsForm.button.submit",
          })}
        </Button>
      </Grid>
    </Grid>
  );
};

export default injectIntl(SkillsAndJobsForm);
