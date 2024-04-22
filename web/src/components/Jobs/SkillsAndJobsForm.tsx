import React from "react";
import { injectIntl } from "react-intl";
import { Button, Grid, TextField } from "@mui/material";
import { Controller, useForm } from "react-hook-form";
import "./SignUp.scss";

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

  const onSubmit = (data: any) => {
    setFormData({ ...formData, ...data });
    // Handle form submission, like navigating to the next page or sending data to backend
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
